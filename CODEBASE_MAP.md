# FTC Robot Codebase Map
**Team Repo:** `adamsaou/3andiBlastiCode` · **Branch:** `master`  
**SDK Base:** FIRST-Tech-Challenge/FtcRobotController (upstream)  
**Language:** Java (Android Studio · Gradle 8.9 · AGP 8.7.0)

---

## Directory Structure

```
FTC/
├── FtcRobotController/          ← SDK base module (DO NOT EDIT — upstream maintained)
│   └── src/main/java/.../
│       ├── internal/            ← Android Activity + OpMode registry
│       └── external/samples/   ← Official FTC sample OpModes (reference only)
│
├── TeamCode/                    ← ★ ALL TEAM CODE LIVES HERE ★
│   ├── build.gradle             ← Gradle module config (applies build.common.gradle)
│   └── src/main/java/org/firstinspires/ftc/teamcode/
│       ├── config/              ← Hardware abstraction + constants
│       ├── subsystems/          ← Mechanism controllers
│       ├── macros/              ← Composite action sequences
│       └── opmodes/             ← OpMode entry points (shown in Driver Hub)
│
├── build.gradle                 ← Top-level build (do not modify)
├── build.common.gradle          ← Shared Android build settings
├── build.dependencies.gradle    ← SDK dependency declarations
├── .github/
│   ├── workflows/               ← GitHub Actions CI
│   ├── CONTRIBUTING.md          ← Team Git workflow guide
│   └── PULL_REQUEST_TEMPLATE.md ← PR checklist
└── CODEBASE_MAP.md              ← This file
```

---

## TeamCode Package Deep Dive

### `config/` — Hardware Layer

| File | Role |
|------|------|
| `HardwareBot.java` | Single hardware map for the robot — initializes all motors/servos from the REV Control Hub config. Only class that calls `hwMap.get(...)`. |
| `RobotConstants.java` | All magic strings and tunable numbers in one place. Change hardware config names or speeds **here only**. |

**HardwareBot hardware inventory:**
| Field | Type | Config Name | Notes |
|-------|------|-------------|-------|
| `leftFront` | `DcMotor` | `"leftFront"` | Direction: REVERSE |
| `rightFront` | `DcMotor` | `"rightFront"` | Direction: FORWARD |
| `leftBack` | `DcMotor` | `"leftBack"` | Direction: REVERSE |
| `rightBack` | `DcMotor` | `"rightBack"` | Direction: FORWARD |
| `intake` | `DcMotor` | `"intake"` | — |
| `outake` | `Servo` | `"outtake"` | Note: field name typo — `outake` vs config name `outtake` |

**All drive motors:** `ZeroPowerBehavior.BRAKE`

**RobotConstants tunable values:**
```java
DRIVE_SPEED  = 0.85   // Max drivetrain power multiplier
INTAKE_POWER = 0.7    // Intake motor power (forward/reverse)
```

---

### `subsystems/` — Mechanism Controllers

Each subsystem takes a `HardwareBot` reference in its constructor and wraps hardware calls into meaningful actions. **No subsystem talks to HardwareMap directly.**

#### `Drivetrain.java`
Implements mecanum holonomic drive with power normalization.

```
drive(y, x, rx)
  y  = forward/backward  (left stick Y, negated for intuitive control)
  x  = strafe left/right (left stick X)
  rx = rotation          (right stick X)

Power formula (normalized to max of 1.0):
  leftFront  = (y + x + rx) / denom * DRIVE_SPEED
  leftBack   = (y - x + rx) / denom * DRIVE_SPEED
  rightFront = (y - x - rx) / denom * DRIVE_SPEED
  rightBack  = (y + x - rx) / denom * DRIVE_SPEED
```

#### `Intake.java`
Simple 3-state control for the intake DC motor.

| Method | Action |
|--------|--------|
| `start()` | `+INTAKE_POWER` (pull in) |
| `reverse()` | `-INTAKE_POWER` (push out) |
| `stop()` | `0` power |

#### `Outtake.java`
Servo-based outtake with position control.

| Method | Servo Position | State |
|--------|---------------|-------|
| `open()` | `1.0` | Fully open |
| `close()` | `0.0` | Fully closed |
| `neutral()` | `0.5` | Neutral / mid position |

---

### `macros/` — Composite Action Sequences

#### `ScoringSequences.java`
Orchestrates multi-subsystem actions. Currently has one macro:

| Method | What it does |
|--------|-------------|
| `quickDeposit()` | Stops intake → opens outtake → (TODO: add timed deposit logic) |

> 📌 This class is designed for future expansion with `sleep()` / `ElapsedTime` timed sequences or state-machine logic for full autonomous scoring routines.

---

### `opmodes/` — OpMode Entry Points

#### `TeleOpMain.java`  `@TeleOp(name="Main Manual")`
Driver-controlled period.

**Gamepad 1 Controls:**
| Input | Action |
|-------|--------|
| Left stick Y (negated) | Forward / Backward |
| Left stick X | Strafe left / right |
| Right stick X | Rotate left / right |
| A button | Start intake |
| B button | Reverse intake |
| (none pressed) | Stop intake |

**Flow:** `init hardware → init subsystems → waitForStart() → loop`

> ⚠️ `Outtake` is **not instantiated** in TeleOpMain yet. Add the import, create the subsystem, and bind gamepad buttons (e.g. `gamepad1.x` / `gamepad1.y`).

#### `AutoMain.java`  `@Autonomous(name="Main Auto", group="Linear Opmode")`
Autonomous period. Currently scaffolded — movement code is TODO.

**Pre-start position selection (D-pad, before `waitForStart`):**
| Input | Sets `startPosition` |
|-------|---------------------|
| D-pad LEFT | `"LEFT"` |
| D-pad RIGHT | `"RIGHT"` |

**Flow:** `init hardware → init drivetrain → position selection loop → waitForStart() → (TODO movements)`

---

## Data / Dependency Flow

```
RobotConstants
      │
      ▼
HardwareBot ──────────────────────────────┐
      │                                   │
      ├──▶ Drivetrain                     │
      ├──▶ Intake            (all consume HardwareBot)
      └──▶ Outtake                        │
                                          │
ScoringSequences ◀──── Intake + Outtake   │
                                          │
TeleOpMain ◀─── HardwareBot + Drivetrain + Intake
AutoMain   ◀─── HardwareBot + Drivetrain
```

**Rule: OpModes instantiate subsystems. Subsystems talk only to HardwareBot. HardwareBot talks to HardwareMap.**

---

## Known Issues / TODOs

| # | Location | Issue |
|---|----------|-------|
| 1 | `AutoMain.java:36` | `//todo Add movement code` — autonomous is a skeleton |
| 2 | `ScoringSequences.java:19` | `// todo: timers/logic here` — deposit has no timing |
| 3 | `TeleOpMain.java` | `Outtake` is not instantiated and has no gamepad bindings |

---

## How to Add a New Subsystem

1. Create `TeamCode/.../subsystems/MySubsystem.java`
2. Constructor: `public MySubsystem(HardwareBot robot) { this.robot = robot; }`
3. Add hardware fields to `HardwareBot.java`, add config name string to `RobotConstants.java`
4. Initialize in `HardwareBot.init()`
5. Instantiate in the relevant OpMode(s): `mySubsystem = new MySubsystem(robot);`

## How to Add a New OpMode

1. Create a new `.java` file in `opmodes/`
2. Extend `LinearOpMode` (preferred) or `OpMode` (iterative)
3. Annotate with `@TeleOp(name="...")` or `@Autonomous(name="...")`
4. Initialize `HardwareBot` + desired subsystems, call `waitForStart()`, then loop

---

## Build & Deploy

```bash
# Build the debug APK
./gradlew :TeamCode:assembleDebug

# Install to connected device
./gradlew :TeamCode:installDebug
```
Or use Android Studio → **Run** (▶) button with Control Hub connected via USB.

Hardware config names in the REV Driver Hub **must exactly match** the strings in `RobotConstants.java`.
