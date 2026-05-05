# Contributing — Team Git Workflow

> This guide is for **our team only** (repo: `adamsaou/3andiBlastiCode`).  
> Adam is the sole reviewer and maintainer of the `master` branch.

---

## Branch Strategy

```
master          ← stable, competition-ready code (protected, no direct pushes)
  └── feature/your-name/short-description    ← your working branch
```

**Never commit directly to `master`.** All changes go through a Pull Request so Adam can review and approve them first.

---

## Step-by-Step Workflow

### 1. Pull the latest master before starting anything

```bash
git checkout master
git pull origin master
```

### 2. Create your feature branch

Name it clearly — use your name so Adam knows who wrote it:

```bash
git checkout -b feature/yourname/what-you-are-doing
# Examples:
# feature/zakaria/outtake-gamepad-bindings
# feature/yassine/auto-movement-left-path
# feature/adam/scoring-sequence-timer
```

### 3. Write your code

- Edit only files inside `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/`
- Build and test locally before pushing:
  ```bash
  ./gradlew :TeamCode:assembleDebug
  ```
- Deploy to the Control Hub and test on the real robot if possible

### 4. Commit often with clear messages

```bash
git add .
git commit -m "Add outtake open/close bindings to TeleOpMain (X/Y buttons)"
```

Use plain English — one sentence describing **what** changed and **why**:
- ✅ `"Fix mecanum strafe direction for right side"`
- ✅ `"Add quickDeposit timer logic in ScoringSequences"`  
- ❌ `"fix"`  
- ❌ `"changes"`

### 5. Push your branch to GitHub

```bash
git push origin feature/yourname/what-you-are-doing
```

### 6. Open a Pull Request

1. Go to [github.com/adamsaou/3andiBlastiCode](https://github.com/adamsaou/3andiBlastiCode)
2. Click **"Compare & pull request"** on your branch
3. Fill out the PR template (what changed, how to test it)
4. Set **base branch: `master`**, **compare: your feature branch**
5. Assign **@adamsaou** as reviewer
6. Click **"Create pull request"**

### 7. Wait for CI and review

- The CI check (`.github/workflows/ci.yml`) will automatically run a Gradle build. It must pass (green ✅) before Adam will review.
- Adam will either approve and merge, or leave comments requesting changes.
- **Do not merge your own PR** — only Adam merges into master.

### 8. After merge — clean up

```bash
git checkout master
git pull origin master
git branch -d feature/yourname/what-you-are-doing    # delete local branch
```

---

## Permissions Summary

| Who | `master` | Feature branches | Merge to `master` |
|-----|----------|-----------------|-------------------|
| Adam (owner) | ✅ Full access | ✅ | ✅ Only he merges |
| Team members | ❌ No direct push | ✅ Push freely | ❌ — open a PR |

> Branch protection is configured in **Settings → Branches → Branch protection rules** on GitHub. See `BRANCH_PROTECTION_SETUP.md` for the exact settings.

---

## Rules & Standards

### Code quality
- Meaningful variable/method names (no `a`, `b`, `temp`)
- Add hardware names to `RobotConstants.java` — never hardcode strings like `"motor1"` directly
- One concern per class (Drivetrain does driving, Intake does intake — don't mix)
- If you add a subsystem, add a corresponding section in `CODEBASE_MAP.md`

### Safety
- Never set motor power above `1.0`
- Always call `intake.stop()` / set servo to safe position in the `stop()` path of your OpMode
- Test new autonomous code at **slow speed** first — comment-guard full-speed sections

### What not to touch
- `FtcRobotController/` — this is the upstream SDK. We only update it by merging from upstream
- `build.gradle` (root) and `build.common.gradle` — only Adam modifies these
- `.github/workflows/` — only Adam modifies these

---

## Syncing with the Official FTC SDK (upstream)

If the official FTC SDK releases an update and Adam decides to pull it in:

```bash
git remote add upstream https://github.com/FIRST-Tech-Challenge/FtcRobotController.git
git fetch upstream
git merge upstream/master
# Resolve any conflicts (usually none — our team code is isolated in TeamCode/)
git push origin master
```

---

## Quick Reference

```bash
# Check what branch you're on
git branch

# See what changed locally
git status
git diff

# Undo changes to a file (before committing)
git checkout -- path/to/file.java

# Pull latest from master to your feature branch (stay up-to-date)
git pull origin master
```

Questions? Ask Adam.
