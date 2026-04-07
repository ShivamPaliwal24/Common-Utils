# Quick Reference Card

## 🚀 Fast Track to Maven Central

### 1️⃣ Setup (One-time)

```bash
# Register at central.sonatype.com with GitHub
# Namespace: io.github.YOUR_USERNAME (instant verification!)

# Install GPG
choco install gnupg  # Windows
brew install gnupg   # Mac

# Generate GPG key
gpg --gen-key

# Publish GPG key
gpg --list-keys  # Copy your key ID
gpg --keyserver keyserver.ubuntu.com --send-keys YOUR_KEY_ID

# Get deployment token from central.sonatype.com
# Profile → Generate User Token → Copy username & password

# Configure Maven settings
cp settings.xml.template ~/.m2/settings.xml
# Edit ~/.m2/settings.xml with token and GPG passphrase
```

### 2️⃣ Customize Project

```xml
<!-- Update pom.xml -->
<groupId>io.github.YOUR_USERNAME</groupId>
<artifactId>common-utils</artifactId>
<version>1.0.0</version>
<url>https://github.com/YOUR_USERNAME/common-utils</url>

<!-- Update developer info and SCM URLs -->
```

```java
// Rename package: com.commonUtils → io.github.yourusername
// Update all Java files and configuration
```

### 3️⃣ Deploy

```bash
# Build and test
mvn clean verify

# Deploy to Maven Central
mvn clean deploy

# Or use helper script
./deploy.sh      # Linux/Mac
deploy.bat       # Windows
```

### 4️⃣ Verify

```
✅ Check status: https://central.sonatype.com/publishing/deployments
⏱️ Wait 15-30 minutes
🔍 Search: https://central.sonatype.com/
```

---

## 📂 Project Files

| File | Purpose |
|------|---------|
| **pom.xml** | Maven configuration, dependencies, publishing setup |
| **GETTING_STARTED.md** | Complete step-by-step setup guide |
| **CENTRAL_PUBLISHER_GUIDE.md** | Detailed publishing guide |
| **PRE_DEPLOYMENT_CHECKLIST.md** | Checklist before deployment |
| **QUICK_START.md** | Build and test locally |
| **README.md** | Main documentation and usage examples |
| **settings.xml.template** | Maven settings template |
| **deploy.sh / deploy.bat** | Deployment helper scripts |

---

## 🛠️ Common Commands

### Build & Test

```bash
# Clean build
mvn clean install

# Run tests
mvn test

# Create JARs (main, sources, javadoc)
mvn clean package

# Full verification (required before deploy)
mvn clean verify
```

### Deployment

```bash
# Deploy to Maven Central
mvn clean deploy

# Deploy with verbose output
mvn clean deploy -X

# Skip tests (not recommended)
mvn clean deploy -DskipTests
```

### GPG Commands

```bash
# List keys
gpg --list-keys
gpg --list-secret-keys

# Generate new key
gpg --gen-key

# Publish key
gpg --keyserver keyserver.ubuntu.com --send-keys KEY_ID

# Test signing
gpg --armor --detach-sign pom.xml
```

---

## 📋 Pre-Deployment Checklist

- [ ] Registered on central.sonatype.com
- [ ] Namespace verified (io.github.username)
- [ ] GPG keys generated and published
- [ ] Deployment token obtained
- [ ] ~/.m2/settings.xml configured
- [ ] pom.xml updated (groupId, URLs, developer info)
- [ ] Package names renamed (com.commonUtils → your namespace)
- [ ] `mvn clean verify` succeeds
- [ ] All 3 JARs created with .asc signatures

---

## 🎯 Your Verified Namespace

**Recommended for instant verification:**

```
io.github.YOUR_GITHUB_USERNAME
```

**Examples:**
- `io.github.johndoe`
- `io.github.janedeveloper`

**Other options:**
- `io.gitlab.username` (GitLab)
- `io.bitbucket.username` (Bitbucket)
- `com.yourcompany` (requires domain verification)

---

## 📦 Using Your Published Library

Once live on Maven Central:

```xml
<dependency>
    <groupId>io.github.YOUR_USERNAME</groupId>
    <artifactId>common-utils</artifactId>
    <version>1.0.0</version>
</dependency>
```

```java
import io.github.yourusername.utils.*;

// Use utilities
String result = StringUtils.capitalize("hello");
LocalDate date = DateUtils.getCurrentDate();
String json = JsonUtils.toJson(myObject);
List<String> first = CollectionUtils.getFirst(myList);
```

---

## ⚠️ Troubleshooting

| Issue | Solution |
|-------|----------|
| GPG signing fails | `gpg --gen-key` then publish key |
| Authentication fails | Check token in ~/.m2/settings.xml |
| Build fails | Verify Java 17+, Maven 3.6+ |
| Validation fails | Check required POM fields |
| Namespace error | Use io.github.username or verify domain |

---

## 🔗 Important Links

- **Sonatype Central Portal**: https://central.sonatype.com/
- **Search Maven Central**: https://central.sonatype.com/
- **Publishing Deployments**: https://central.sonatype.com/publishing/deployments
- **Documentation**: https://central.sonatype.org/

---

## 🔄 Release Process

```bash
# 1. Update version in pom.xml
<version>1.0.1</version>

# 2. Commit and tag
git add .
git commit -m "Release v1.0.1"
git tag -a v1.0.1 -m "Release version 1.0.1"
git push origin main --tags

# 3. Deploy
mvn clean deploy

# 4. Verify on central.sonatype.com
```

---

## ✅ Success Indicators

After deployment:

1. ✅ `mvn deploy` completes without errors
2. ✅ Status shows "PUBLISHED" on central.sonatype.com/publishing/deployments
3. ✅ Artifact appears in search within 30 minutes
4. ✅ Can import in test project
5. ✅ Auto-configuration works in Spring Boot app

---

**Need more details?** See [GETTING_STARTED.md](GETTING_STARTED.md) for complete instructions.
