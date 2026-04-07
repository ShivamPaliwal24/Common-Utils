# Pre-Deployment Checklist

Use this checklist before deploying to Maven Central to ensure everything is configured correctly.

## ✅ Account Setup

- [ ] Created account at https://central.sonatype.com/
- [ ] Verified namespace (e.g., `io.github.yourusername`)
- [ ] Generated deployment token from profile settings
- [ ] Saved token username and password securely

## ✅ GPG Keys Setup

- [ ] Installed GPG (`gpg --version` works)
- [ ] Generated GPG key pair (`gpg --gen-key`)
- [ ] Remember GPG passphrase
- [ ] Published public key to key servers:
  ```bash
  gpg --keyserver keyserver.ubuntu.com --send-keys YOUR_KEY_ID
  gpg --keyserver keys.openpgp.org --send-keys YOUR_KEY_ID
  ```
- [ ] Backed up private key (stored securely, NOT in Git)

## ✅ Maven Configuration

- [ ] Maven 3.6+ installed (`mvn -version` works)
- [ ] Created `~/.m2/settings.xml` from `settings.xml.template`
- [ ] Updated `settings.xml` with:
  - [ ] Deployment token username
  - [ ] Deployment token password
  - [ ] GPG passphrase

## ✅ Project Configuration (pom.xml)

- [ ] Updated `<groupId>` to match verified namespace
  - Example: `io.github.yourusername`
- [ ] Set appropriate `<artifactId>`
  - Example: `common-utils`
- [ ] Set version number (e.g., `1.0.0`)
- [ ] Filled in project metadata:
  - [ ] `<name>` - Project name
  - [ ] `<description>` - Project description
  - [ ] `<url>` - Project URL (GitHub, etc.)
- [ ] Configured `<licenses>` section
- [ ] Updated `<developers>` with your information
- [ ] Updated `<scm>` with your repository URLs

### Required POM Sections Checklist

```xml
✅ <groupId> - Must match verified namespace
✅ <artifactId> - Unique artifact name
✅ <version> - Semantic version (e.g., 1.0.0)
✅ <name> - Human-readable name
✅ <description> - Clear description
✅ <url> - Project homepage
✅ <licenses> - At least one license
✅ <developers> - At least one developer
✅ <scm> - Source control info
```

## ✅ Package Names Updated

- [ ] Renamed `com.yourcompany` to your namespace in all Java files:
  - [ ] `src/main/java/com/yourcompany/config/UtilsAutoConfiguration.java`
  - [ ] `src/main/java/com/yourcompany/config/UtilsProperties.java`
  - [ ] `src/main/java/com/yourcompany/utils/StringUtils.java`
  - [ ] `src/main/java/com/yourcompany/utils/DateUtils.java`
  - [ ] `src/main/java/com/yourcompany/utils/JsonUtils.java`
  - [ ] `src/main/java/com/yourcompany/utils/CollectionUtils.java`
- [ ] Updated `@ComponentScan` in `UtilsAutoConfiguration.java`
- [ ] Updated package in `org.springframework.boot.autoconfigure.AutoConfiguration.imports`

## ✅ Build Verification

Run these commands to verify everything works:

### 1. Clean Build
```bash
mvn clean
```
- [ ] Completes without errors

### 2. Compile
```bash
mvn compile
```
- [ ] Compiles successfully
- [ ] No compilation errors

### 3. Run Tests
```bash
mvn test
```
- [ ] All tests pass (or no tests present)

### 4. Package
```bash
mvn package
```
- [ ] Creates JAR in `target/` directory
- [ ] Main JAR created: `common-utils-1.0.0.jar`

### 5. Full Verification
```bash
mvn clean verify
```
- [ ] Completes successfully
- [ ] Creates all required JARs:
  - [ ] Main JAR: `common-utils-1.0.0.jar`
  - [ ] Sources JAR: `common-utils-1.0.0-sources.jar`
  - [ ] Javadoc JAR: `common-utils-1.0.0-javadoc.jar`
- [ ] All JARs have `.asc` signature files

## ✅ Git Repository

- [ ] Code committed to Git
- [ ] Repository pushed to GitHub/GitLab/etc.
- [ ] Repository is public (for open source)
- [ ] README.md is complete and informative
- [ ] LICENSE file exists

## ✅ Documentation

- [ ] README.md has usage examples
- [ ] All public classes have Javadoc comments
- [ ] Version documented in README changelog

## ✅ Final Checks

- [ ] No hardcoded credentials in code
- [ ] No test credentials in properties
- [ ] `.gitignore` excludes sensitive files
- [ ] No snapshot dependencies (version must not end with -SNAPSHOT for release)
- [ ] Version number is correct (increment from last release)

## ✅ Deployment Day

### Before Running `mvn deploy`:

1. [ ] Double-check version number in `pom.xml`
2. [ ] Ensure all changes are committed
3. [ ] Tag the release in Git:
   ```bash
   git tag -a v1.0.0 -m "Release version 1.0.0"
   git push origin v1.0.0
   ```

### Run Deployment:

```bash
# Option 1: Use deployment script (recommended)
./deploy.sh        # Linux/Mac
deploy.bat         # Windows

# Option 2: Manual deployment
mvn clean deploy
```

### After Deployment:

- [ ] Check deployment status at https://central.sonatype.com/publishing/deployments
- [ ] Wait for PUBLISHED status (5-15 minutes)
- [ ] Verify artifact appears on Maven Central Search
- [ ] Test importing the dependency in a test project
- [ ] Update documentation with new version
- [ ] Announce the release (GitHub releases, social media, etc.)

## Common Issues

### ❌ GPG Signing Fails
**Fix:** 
```bash
# Check GPG keys
gpg --list-secret-keys

# If no keys, generate one
gpg --gen-key
```

### ❌ Authentication Fails
**Fix:** 
- Verify token in `~/.m2/settings.xml`
- Ensure `<id>central</id>` matches in pom.xml and settings.xml
- Regenerate token if needed

### ❌ Namespace Not Verified
**Fix:**
- Use `io.github.yourusername` for instant verification
- Or complete domain verification process

### ❌ Missing Required Metadata
**Fix:**
- Ensure all required POM sections are complete
- Check: name, description, url, licenses, developers, scm

### ❌ Package Names Don't Match Namespace
**Fix:**
- Rename all packages from `com.yourcompany` to `io.github.yourusername`
- Update imports in all files

## Ready to Deploy?

If you've checked all items above, you're ready to deploy!

```bash
# Final verification
mvn clean verify

# Deploy
mvn clean deploy
```

Good luck! 🚀
