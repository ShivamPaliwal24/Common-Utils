# Complete Setup Guide - Ready to Deploy!

## ✅ What's Done:

1. **GPG Keys Generated Successfully!**
   - Key ID: `6E3F4D00FFA67B6B457A7F511026B81AF2F36397`
   - Name: shivam paliwal
   - Email: shivam.paliwal74484@gmail.com
   - Valid until: 2029-04-06

2. **Sonatype Token Configured:**
   - Already in `settings.xml`

---

## 🚀 Next Steps to Publish:

### Step 1: Publish Your GPG Public Key (REQUIRED)

Run these commands:

```bash
gpg --keyserver keyserver.ubuntu.com --send-keys 6E3F4D00FFA67B6B457A7F511026B81AF2F36397
gpg --keyserver keys.openpgp.org --send-keys 6E3F4D00FFA67B6B457A7F511026B81AF2F36397
```

You should see messages like:
```
gpg: sending key 1026B81AF2F36397 to hkp://keyserver.ubuntu.com
```

---

### Step 2: Update settings.xml with Your GPG Passphrase

1. Open `D:\DependencyCreator\settings.xml` in a text editor
2. Find line 24 (in the `<properties>` section)
3. Uncomment and add your GPG passphrase:

**Change from:**
```xml
<!-- <gpg.passphrase>YOUR_GPG_PASSPHRASE</gpg.passphrase> -->
```

**To:**
```xml
<gpg.passphrase>YourActualGPGPassphrase</gpg.passphrase>
```

Replace `YourActualGPGPassphrase` with the passphrase you created when running `gpg --gen-key`

4. Save the file

---

### Step 3: Copy settings.xml to Maven Directory

```bash
copy D:\DependencyCreator\settings.xml %USERPROFILE%\.m2\settings.xml
```

Or manually:
- Copy `D:\DependencyCreator\settings.xml`
- Paste to `C:\Users\ShivamPaliwal\.m2\settings.xml`

---

### Step 4: Verify Your Namespace on Sonatype Central

1. Go to https://central.sonatype.com/
2. Sign in (you should already be logged in)
3. Click your profile icon → **"Namespaces"**
4. Check what namespace you have verified

**Common options:**
- `io.github.yourGitHubUsername` (instant verification if you have GitHub)
- `com.yourcompany` (requires domain verification)

**Write down your verified namespace - you'll need it in the next step!**

---

### Step 5: Update pom.xml

Open `D:\DependencyCreator\pom.xml` and make these changes:

#### Change 1: GroupId (Line 7)
Replace with YOUR verified namespace from Step 4:
```xml
<groupId>io.github.YOUR_GITHUB_USERNAME</groupId>
```

Example: If your GitHub username is "shivampaliwal", use:
```xml
<groupId>io.github.shivampaliwal</groupId>
```

#### Change 2: Developer Info (Lines 28-32)
```xml
<developer>
    <id>shivampaliwal</id>
    <name>shivam paliwal</name>
    <email>shivam.paliwal74484@gmail.com</email>
</developer>
```

#### Change 3: Project URL (Line 13)
```xml
<url>https://github.com/YOUR_USERNAME/common-utils</url>
```

#### Change 4: SCM URLs (Lines 36-39)
```xml
<scm>
    <connection>scm:git:git://github.com/YOUR_USERNAME/common-utils.git</connection>
    <developerConnection>scm:git:ssh://github.com:YOUR_USERNAME/common-utils.git</developerConnection>
    <url>https://github.com/YOUR_USERNAME/common-utils</url>
</scm>
```

---

### Step 6: Update Package Names

You need to rename the package from `com.commonUtils` to match your namespace.

**Example:** If your namespace is `io.github.shivampaliwal`:

#### 6a. Rename the folder structure:
- From: `src/main/java/com/commonUtils/`
- To: `src/main/java/io/github/shivampaliwal/`

#### 6b. Update package in ALL Java files:

**Files to update:**
1. `src/main/java/.../config/UtilsAutoConfiguration.java`
2. `src/main/java/.../config/UtilsProperties.java`
3. `src/main/java/.../utils/StringUtils.java`
4. `src/main/java/.../utils/DateUtils.java`
5. `src/main/java/.../utils/JsonUtils.java`
6. `src/main/java/.../utils/CollectionUtils.java`

Change the first line in each file:
```java
// FROM:
package com.commonUtils.config;
package com.commonUtils.utils;

// TO:
package io.github.shivampaliwal.config;
package io.github.shivampaliwal.utils;
```

#### 6c. Update ComponentScan in UtilsAutoConfiguration.java:
```java
@ComponentScan(basePackages = "io.github.shivampaliwal")
```

#### 6d. Update auto-configuration imports:
File: `src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`

Change:
```
io.github.shivampaliwal.config.UtilsAutoConfiguration
```

---

### Step 7: Test Build

```bash
cd D:\DependencyCreator
mvn clean verify
```

**Expected output:**
```
[INFO] BUILD SUCCESS
```

**Check that these files are created in `target/` folder:**
- ✅ `common-utils-1.0.0.jar`
- ✅ `common-utils-1.0.0-sources.jar`
- ✅ `common-utils-1.0.0-javadoc.jar`
- ✅ `.asc` files (GPG signatures)

---

### Step 8: Deploy to Maven Central! 🚀

```bash
mvn clean deploy
```

**What happens:**
1. Compiles code
2. Runs tests
3. Creates JARs (main, sources, javadoc)
4. Signs all JARs with your GPG key
5. Uploads to central.sonatype.com
6. Validates requirements
7. Auto-publishes to Maven Central

**This will take a few minutes...**

---

### Step 9: Monitor Deployment

1. Go to https://central.sonatype.com/publishing/deployments
2. You'll see your deployment with status changing:
   - **VALIDATING** → Checking requirements (2-5 min)
   - **VALIDATED** → Passed all checks
   - **PUBLISHING** → Publishing to Maven Central (5-10 min)
   - **PUBLISHED** → ✅ Live on Maven Central!

Total time: **15-30 minutes**

---

### Step 10: Verify and Use Your Library

**Search on Maven Central:**
- Go to https://central.sonatype.com/
- Search: `io.github.yourname:common-utils`

**Use in any Spring Boot project:**
```xml
<dependency>
    <groupId>io.github.yourname</groupId>
    <artifactId>common-utils</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

## ✅ Pre-Deployment Checklist

Before running `mvn clean deploy`, verify:

- [ ] Published GPG key to keyservers (Step 1)
- [ ] Updated settings.xml with GPG passphrase (Step 2)
- [ ] Copied settings.xml to `%USERPROFILE%\.m2\` (Step 3)
- [ ] Verified namespace on central.sonatype.com (Step 4)
- [ ] Updated pom.xml groupId to match verified namespace (Step 5)
- [ ] Updated developer name and email in pom.xml (Step 5)
- [ ] Updated all URLs in pom.xml (Step 5)
- [ ] Renamed package folders (Step 6)
- [ ] Updated package declaration in all Java files (Step 6)
- [ ] Updated @ComponentScan in UtilsAutoConfiguration.java (Step 6)
- [ ] Updated auto-configuration imports file (Step 6)
- [ ] `mvn clean verify` succeeded (Step 7)

---

## 🎉 You're Ready!

**Start with Step 1 and follow through to Step 10.**

If you encounter any errors during deployment, check:
- GPG passphrase is correct in settings.xml
- Namespace in pom.xml exactly matches verified namespace on central.sonatype.com
- All package names updated consistently
- Internet connection is stable

**Good luck! You're about to publish your first library to Maven Central!** 🚀
