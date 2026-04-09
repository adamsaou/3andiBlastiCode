# FTC Robot Architecture Overview

## 1. Data Flow


- OpModes handle input only
- Subsystems handle logic
- HardwareBot is the only class that talks to hardware
- RobotConstants provides shared values (speed, names, etc.)

---

## 2. What to Improve Next

- **Command Pattern**  
  Wrap actions into reusable commands

- **State Machines**  
  Avoid repeated button triggers using states (RUNNING, STOPPED, etc.)

- **Async Timing (IMPORTANT)**  
  Use `ElapsedTime` instead of `Thread.sleep()`

---

## 3. Reusable Components

Reusable in any robot:
- Drivetrain (mecanum logic)
- HardwareBot structure
- RobotConstants
- Intake subsystem

Not reusable:
- ScoringSequences (game-specific)
- AutoMain logic
- Servo positions (should be moved to constants)

---

## 4. Future Improvements

- Split HardwareBot into smaller parts (Drivetrain, Intake, etc.)
- Move servo values into RobotConstants
- Add timers to ScoringSequences
- Improve TeleOp button handling (use toggles instead of hold)

---
