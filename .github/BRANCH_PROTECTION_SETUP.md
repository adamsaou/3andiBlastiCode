# Branch Protection Setup (Adam Only)

This is a one-time setup on GitHub to enforce the team workflow.  
Only the repo owner (Adam) needs to do this.

---

## Step-by-Step: Protect `master`

1. Go to **github.com/adamsaou/3andiBlastiCode**
2. Click **Settings** (top tab, requires owner access)
3. In the left sidebar, click **Branches**
4. Under "Branch protection rules", click **Add branch protection rule**

### Rule settings (fill these in):

**Branch name pattern:** `master`

Then check these boxes:

| Setting | Enable? | Why |
|---------|---------|-----|
| ✅ Require a pull request before merging | YES | No one can push directly to master |
| ↳ Required approvals | **1** | Adam must approve every PR |
| ↳ Dismiss stale pull request approvals when new commits are pushed | YES | Forces re-review if someone pushes more commits |
| ✅ Require status checks to pass before merging | YES | CI build must be green |
| ↳ Status check: **Gradle Build Check** | YES | The CI job from `ci.yml` |
| ↳ Require branches to be up to date before merging | YES | Prevents merging outdated branches |
| ✅ Require conversation resolution before merging | YES | All review comments must be resolved |
| ❌ Allow force pushes | NO | Prevents history rewrites |
| ❌ Allow deletions | NO | Prevents accidental branch deletion |
| ✅ Do not allow bypassing the above settings | YES | Even Adam goes through a PR (optional — you can leave this OFF if you want to be able to hotfix directly) |

5. Click **Create** (or **Save changes**)

---

## Recommended Team Member Permissions

Go to **Settings → Collaborators and teams → Manage access**:

| Team Member | Role | What they can do |
|-------------|------|-----------------|
| Adam | **Owner / Admin** | Full repo access, merge PRs, change settings |
| Other members | **Write** | Push to feature branches, open PRs — cannot push to master directly |

To add a collaborator: **Settings → Collaborators → Add people** → enter their GitHub username → select **Write** role.

---

## After Setup

Once branch protection is active:
- Any push directly to `master` (e.g. `git push origin master`) will be **rejected by GitHub**
- PRs targeting `master` will show CI status and require Adam's approval before the merge button activates
- Team members can still push freely to their own `feature/*` branches

That's it — the workflow enforces itself from this point.
