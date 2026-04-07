# Sonatype Central Publisher Guide

This guide explains how to publish your library to Maven Central using the new **central.sonatype.com** portal (Sonatype Central Publisher). This is the modern, simpler way to publish compared to the legacy OSSRH method.

## Overview

The Central Publisher offers:
- ✅ Simpler registration process (no JIRA tickets!)
- ✅ Faster namespace verification
- ✅ Modern web UI for managing deployments
- ✅ Token-based authentication
- ✅ Automatic publishing option
- ✅ Better deployment tracking

## Prerequisites

Before you start, ensure you have:
- Java 17 or higher installed
- Maven 3.6+ installed
- A GitHub, Google, or other supported account for login
- GPG keys for signing artifacts

## Step 1: Register on Sonatype Central

### 1.1 Create Account

1. Go to https://central.sonatype.com/
2. Click **"Sign In"** in the top right
3. Sign in with one of the supported providers:
   - GitHub (recommended)
   - Google
   - Or create a new account

### 1.2 Verify Your Namespace

After signing in, you need to verify a namespace (groupId):

#### Option A: Using GitHub Account (Recommended)

1. Click on your profile → **"Namespaces"**
2. Click **"Add Namespace"**
3. Enter: `io.github.YOUR_GITHUB_USERNAME`
   - Example: `io.github.johndoe`
4. Verification is **automatic** for GitHub users! ✅

#### Option B: Using Custom Domain

1. Click **"Add Namespace"**
2. Enter your domain in reverse: `com.yourcompany`
3. You'll need to verify ownership by:
   - Adding a DNS TXT record, OR
   - Uploading a verification file to your website

#### Option C: Using Other Git Hosting

- GitLab: `io.gitlab.username`
- Bitbucket: `io.bitbucket.username`
- Gitee: `io.gitee.username`

**Verification is instant for all Git hosting options!**

## Step 2: Generate GPG Keys

GPG keys are required to sign your artifacts (Maven Central requirement).

### 2.1 Install GPG

**Windows:**
```bash
# Download from https://www.gnupg.org/download/
# Or use Chocolatey:
choco install gnupg
```

**Mac:**
```bash
brew install gnupg
```

**Linux:**
```bash
sudo apt-get install gnupg  # Debian/Ubuntu
```

### 2.2 Generate Key Pair

```bash
# Generate a new GPG key
gpg --gen-key

# Follow the prompts:
# 1. Use default (RSA and RSA)
# 2. Key size: 2048 or 4096 bits
# 3. Expiration: 0 (never) or set a date
# 4. Enter your name and email
# 5. Create a STRONG passphrase (remember it!)
```

### 2.3 Publish Public Key

```bash
# List your keys
gpg --list-keys

# You'll see output like:
# pub   rsa3072 2024-01-01 [SC]
#       1234567890ABCDEF1234567890ABCDEF12345678
# uid   [ultimate] Your Name <your.email@example.com>

# Copy the long key ID (40 characters)
# Publish to key servers
gpg --keyserver keyserver.ubuntu.com --send-keys YOUR_KEY_ID
gpg --keyserver keys.openpgp.org --send-keys YOUR_KEY_ID
```

### 2.4 Export Private Key (Backup)

```bash
# Export private key for backup
gpg --export-secret-keys YOUR_KEY_ID > private-key.asc

# Store this file securely! Never commit to Git!
```

## Step 3: Generate Deployment Token

1. Go to https://central.sonatype.com/
2. Click on your profile → **"Generate User Token"**
3. Click **"Generate User Token"**
4. Copy both the **Username** and **Password** shown
   - ⚠️ Save these immediately - you won't see them again!

## Step 4: Configure Maven Settings

Create or edit `~/.m2/settings.xml`:

**Windows:** `C:\Users\YourName\.m2\settings.xml`
**Mac/Linux:** `~/.m2/settings.xml`

```xml
<settings>
    <servers>
        <!-- Sonatype Central Publisher credentials -->
        <server>
            <id>central</id>
            <username>YOUR_TOKEN_USERNAME</username>
            <password>YOUR_TOKEN_PASSWORD</password>
        </server>
    </servers>

    <profiles>
        <profile>
            <id>central-publisher</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
                <!-- GPG Configuration -->
                <gpg.executable>gpg</gpg.executable>
                <gpg.passphrase>YOUR_GPG_PASSPHRASE</gpg.passphrase>
            </properties>
        </profile>
    </profiles>
</settings>
```

**Replace:**
- `YOUR_TOKEN_USERNAME` → Username from Step 3
- `YOUR_TOKEN_PASSWORD` → Password from Step 3
- `YOUR_GPG_PASSPHRASE` → Your GPG key passphrase

## Step 5: Update Project Configuration

### 5.1 Update pom.xml

Update these fields in your `pom.xml`:

```xml
<!-- Use your verified namespace -->
<groupId>io.github.yourusername</groupId>
<artifactId>common-utils</artifactId>
<version>1.0.0</version>

<name>Common Utils Library</name>
<description>A reusable utilities library for Java projects</description>
<url>https://github.com/yourusername/common-utils</url>

<licenses>
    <license>
        <name>The Apache License, Version 2.0</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
    </license>
</licenses>

<developers>
    <developer>
        <id>yourusername</id>
        <name>Your Name</name>
        <email>your.email@example.com</email>
    </developer>
</developers>

<scm>
    <connection>scm:git:git://github.com/yourusername/common-utils.git</connection>
    <developerConnection>scm:git:ssh://github.com:yourusername/common-utils.git</developerConnection>
    <url>https://github.com/yourusername/common-utils</url>
</scm>
```

### 5.2 Update Package Names

Rename all packages to match your namespace:
- From: `com.yourcompany`
- To: `io.github.yourusername` (or your verified namespace)

Files to update:
- All Java files in `src/main/java/`
- `UtilsAutoConfiguration.java` - Update `@ComponentScan`
- `org.springframework.boot.autoconfigure.AutoConfiguration.imports` file

## Step 6: Build and Deploy

### 6.1 Build the Project

```bash
# Clean and verify everything builds correctly
mvn clean verify
```

This will create in `target/`:
- `common-utils-1.0.0.jar` - Main JAR
- `common-utils-1.0.0-sources.jar` - Sources JAR
- `common-utils-1.0.0-javadoc.jar` - Javadoc JAR
- `*.asc` files - GPG signatures for each JAR

### 6.2 Deploy to Central

```bash
# Deploy to Sonatype Central
mvn clean deploy
```

What happens:
1. ✅ Builds all JARs
2. ✅ Signs artifacts with GPG
3. ✅ Uploads to central.sonatype.com
4. ✅ Validates requirements
5. ✅ Automatically publishes (if autoPublish=true)
6. ✅ Syncs to Maven Central

### 6.3 Monitor Deployment

1. Go to https://central.sonatype.com/publishing/deployments
2. You'll see your deployment with status:
   - **VALIDATING** - Checking requirements
   - **VALIDATED** - Ready to publish
   - **PUBLISHING** - Being published
   - **PUBLISHED** - Live on Maven Central! 🎉

The entire process typically takes **5-15 minutes**.

## Step 7: Verify Publication

After successful deployment:

### Check on Maven Central Search

1. Go to https://central.sonatype.com/
2. Search for your artifact: `g:io.github.yourusername a:common-utils`
3. It should appear within 15-30 minutes

### Use in Another Project

Once published, anyone can use your library:

```xml
<dependency>
    <groupId>io.github.yourusername</groupId>
    <artifactId>common-utils</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Troubleshooting

### Issue: GPG signing fails

```bash
# Test GPG
gpg --list-secret-keys

# If empty, you need to generate keys
gpg --gen-key
```

### Issue: Authentication fails

- Double-check token credentials in `~/.m2/settings.xml`
- Server ID must match: `<id>central</id>`
- Regenerate token if needed

### Issue: Namespace not verified

- Use `io.github.yourusername` for instant verification
- For custom domains, complete DNS/file verification

### Issue: Validation fails

Common requirements:
- ✅ Valid POM with all required fields
- ✅ Source JAR included
- ✅ Javadoc JAR included  
- ✅ All artifacts signed with GPG
- ✅ GroupId matches verified namespace

Check validation messages at: https://central.sonatype.com/publishing/deployments

## Advanced: Releasing New Versions

For version 1.0.1, 1.1.0, etc.:

```bash
# 1. Update version in pom.xml
<version>1.0.1</version>

# 2. Update CHANGELOG in README

# 3. Commit changes
git add .
git commit -m "Release version 1.0.1"
git tag v1.0.1
git push origin main --tags

# 4. Deploy
mvn clean deploy
```

## Summary: Quick Steps

1. ✅ Register at central.sonatype.com
2. ✅ Verify namespace (instant for GitHub!)
3. ✅ Generate GPG keys
4. ✅ Generate deployment token
5. ✅ Configure ~/.m2/settings.xml
6. ✅ Update pom.xml with your namespace
7. ✅ Run `mvn clean deploy`
8. ✅ Wait 15 minutes - Done! 🎉

## Resources

- Central Publisher Portal: https://central.sonatype.com/
- Documentation: https://central.sonatype.org/
- Requirements: https://central.sonatype.org/publish/requirements/
- GitHub Verification: https://central.sonatype.org/register/namespace/
