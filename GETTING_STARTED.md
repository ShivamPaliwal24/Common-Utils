# Getting Started - Step-by-Step Guide

This guide will walk you through setting up and publishing this library to Maven Central using **central.sonatype.com**.

## 📚 Project Overview

You now have a complete Spring Boot utilities library ready to be published to Maven Central. Here's what you have:

### ✅ What's Included

- **4 Utility Classes**: StringUtils, DateUtils, JsonUtils, CollectionUtils
- **Spring Boot Auto-Configuration**: Automatic setup when used as a dependency
- **Maven Configuration**: Ready for Maven Central publishing
- **Complete Documentation**: Guides, checklists, and examples
- **Deployment Scripts**: Helper scripts for easy deployment

### 📁 Project Structure

```
D:/DependencyCreator/
├── src/main/java/com/commonUtils/
│   ├── config/           # Spring Boot configuration
│   └── utils/            # Utility classes
├── pom.xml               # Maven configuration
├── README.md             # Main documentation
├── CENTRAL_PUBLISHER_GUIDE.md    # Publishing guide
├── PRE_DEPLOYMENT_CHECKLIST.md  # Checklist before deploy
├── QUICK_START.md        # Quick start guide
└── deploy.bat / deploy.sh       # Deployment scripts
```

## 🎯 Your Path to Maven Central

Follow these steps in order:

### Step 1: Customize the Project (REQUIRED)

Before publishing, you MUST customize the project with your information.

#### 1.1 Register on Sonatype Central

1. Go to https://central.sonatype.com/
2. Click "Sign In" and use GitHub (recommended)
3. Click your profile → "Namespaces" → "Add Namespace"
4. Enter: `io.github.YOUR_GITHUB_USERNAME`
5. ✅ Instant verification for GitHub users!

#### 1.2 Update pom.xml

Open `pom.xml` and update these lines:

```xml
<!-- Line 7: Change to your verified namespace -->
<groupId>io.github.YOUR_GITHUB_USERNAME</groupId>

<!-- Line 8: Keep or change the artifact name -->
<artifactId>common-utils</artifactId>

<!-- Line 9: Set your version -->
<version>1.0.0</version>

<!-- Line 13: Update project URL -->
<url>https://github.com/YOUR_GITHUB_USERNAME/common-utils</url>

<!-- Lines 28-32: Update developer info -->
<developer>
    <id>YOUR_GITHUB_USERNAME</id>
    <name>Your Name</name>
    <email>your.email@example.com</email>
</developer>

<!-- Lines 36-39: Update repository URLs -->
<scm>
    <connection>scm:git:git://github.com/YOUR_GITHUB_USERNAME/common-utils.git</connection>
    <developerConnection>scm:git:ssh://github.com:YOUR_GITHUB_USERNAME/common-utils.git</developerConnection>
    <url>https://github.com/YOUR_GITHUB_USERNAME/common-utils/tree/main</url>
</scm>
```

#### 1.3 Update Package Names

You need to rename packages from `com.commonUtils` to match your namespace.

**Option A: Using IDE (IntelliJ IDEA / Eclipse)**

1. In your IDE, right-click on the `com.commonUtils` package
2. Select "Refactor" → "Rename"
3. Enter your new package name: `io.github.yourusername`
4. Select "Search in comments and strings"
5. Click "Refactor" to apply

**Option B: Manually**

Rename the folder structure:
```
src/main/java/com/commonUtils/
  → src/main/java/io/github/yourusername/
```

Update all Java files to use the new package:
```java
package io.github.yourusername.config;
package io.github.yourusername.utils;
```

Update `UtilsAutoConfiguration.java` line 13:
```java
@ComponentScan(basePackages = "io.github.yourusername")
```

Update `src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`:
```
io.github.yourusername.config.UtilsAutoConfiguration
```

### Step 2: Set Up GPG Keys

#### 2.1 Install GPG

**Windows:**
```bash
choco install gnupg
```

**Mac:**
```bash
brew install gnupg
```

**Linux:**
```bash
sudo apt-get install gnupg
```

#### 2.2 Generate Key

```bash
gpg --gen-key
```

Follow prompts and **remember your passphrase**!

#### 2.3 Publish Key

```bash
# List keys to get your key ID
gpg --list-keys

# Publish (replace YOUR_KEY_ID with actual ID)
gpg --keyserver keyserver.ubuntu.com --send-keys YOUR_KEY_ID
```

### Step 3: Get Deployment Token

1. Go to https://central.sonatype.com/
2. Click your profile → "Generate User Token"
3. **Copy and save** the username and password shown

### Step 4: Configure Maven

#### 4.1 Copy settings template

**Windows:**
```bash
copy settings.xml.template %USERPROFILE%\.m2\settings.xml
```

**Mac/Linux:**
```bash
cp settings.xml.template ~/.m2/settings.xml
```

#### 4.2 Edit settings.xml

Open `~/.m2/settings.xml` and update:

```xml
<username>YOUR_TOKEN_USERNAME</username>     <!-- From Step 3 -->
<password>YOUR_TOKEN_PASSWORD</password>     <!-- From Step 3 -->
<gpg.passphrase>YOUR_GPG_PASSPHRASE</gpg.passphrase>  <!-- From Step 2 -->
```

### Step 5: Build and Test

```bash
# Test build
mvn clean verify
```

You should see:
- ✅ Build SUCCESS
- ✅ 3 JARs created in `target/`
- ✅ All JARs have `.asc` signature files

### Step 6: Deploy to Maven Central

#### Option A: Using Deployment Script (Recommended)

**Windows:**
```bash
deploy.bat
```

**Mac/Linux:**
```bash
chmod +x deploy.sh
./deploy.sh
```

#### Option B: Manual Deployment

```bash
mvn clean deploy
```

### Step 7: Monitor and Verify

1. **Check Deployment Status**
   - Go to https://central.sonatype.com/publishing/deployments
   - Watch status change: VALIDATING → VALIDATED → PUBLISHING → PUBLISHED

2. **Wait for Publication** (15-30 minutes)

3. **Verify on Maven Central**
   - Search: https://central.sonatype.com/
   - Look for: `io.github.YOUR_USERNAME:common-utils`

4. **Test in Another Project**
   ```xml
   <dependency>
       <groupId>io.github.YOUR_USERNAME</groupId>
       <artifactId>common-utils</artifactId>
       <version>1.0.0</version>
   </dependency>
   ```

## 🎉 Success!

Your library is now on Maven Central and anyone can use it!

## 📖 Next Steps

- [ ] Create a GitHub repository and push your code
- [ ] Add a LICENSE file
- [ ] Write more comprehensive tests
- [ ] Add more utility methods
- [ ] Create GitHub release page
- [ ] Share your library with the community

## 📚 Additional Resources

- **[CENTRAL_PUBLISHER_GUIDE.md](CENTRAL_PUBLISHER_GUIDE.md)** - Detailed publishing guide
- **[PRE_DEPLOYMENT_CHECKLIST.md](PRE_DEPLOYMENT_CHECKLIST.md)** - Complete checklist
- **[QUICK_START.md](QUICK_START.md)** - Building and using locally
- **[README.md](README.md)** - Main documentation

## ❓ Need Help?

### Common Issues

**Build Fails:**
- Check Java version: `java -version` (need 17+)
- Check Maven version: `mvn -version` (need 3.6+)

**GPG Errors:**
- Verify GPG installed: `gpg --version`
- Check keys exist: `gpg --list-secret-keys`

**Authentication Fails:**
- Verify token in `~/.m2/settings.xml`
- Ensure `<id>central</id>` matches

**Deployment Fails:**
- Run checklist: [PRE_DEPLOYMENT_CHECKLIST.md](PRE_DEPLOYMENT_CHECKLIST.md)
- Check errors at: https://central.sonatype.com/publishing/deployments

## 🔄 Releasing Future Versions

1. Update version in `pom.xml`
2. Update changelog in `README.md`
3. Commit and tag:
   ```bash
   git tag -a v1.0.1 -m "Release 1.0.1"
   git push origin v1.0.1
   ```
4. Deploy: `mvn clean deploy`

That's it! Happy publishing! 🚀
