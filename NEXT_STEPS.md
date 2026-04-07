# Your Next Steps - Ready to Deploy! 🚀

✅ **GPG is installed!** (Version 2.5.18)  
✅ **Sonatype token is configured!** (in settings.xml)

You're almost ready to publish to Maven Central! Just a few more steps.

---

## Step 1: Generate Your GPG Keys

Run this command in your terminal:

```bash
gpg --gen-key
```

You'll be asked:

1. **Real name**: `Shivam Paliwal` (or your preferred name)
2. **Email address**: Your email (use the same one you'll put in pom.xml)
3. **Passphrase**: Create a STRONG passphrase
   - ⚠️ **VERY IMPORTANT**: Remember this passphrase!
   - Example: `MySecurePass123!@#`
   - You'll need it in the next step

After generation completes, continue below.

---

## Step 2: Get Your GPG Key ID

```bash
gpg --list-keys
```

You'll see output like:
```
pub   rsa3072 2024-01-15 [SC]
      1234567890ABCDEF1234567890ABCDEF12345678
uid           [ultimate] Shivam Paliwal <your.email@example.com>
```

**Copy the 40-character key ID** (the long string of letters/numbers).

---

## Step 3: Publish Your Public Key

Replace `YOUR_KEY_ID` with your actual key ID from Step 2:

```bash
gpg --keyserver keyserver.ubuntu.com --send-keys YOUR_KEY_ID
gpg --keyserver keys.openpgp.org --send-keys YOUR_KEY_ID
```

Example:
```bash
gpg --keyserver keyserver.ubuntu.com --send-keys 1234567890ABCDEF1234567890ABCDEF12345678
gpg --keyserver keys.openpgp.org --send-keys 1234567890ABCDEF1234567890ABCDEF12345678
```

---

## Step 4: Update Maven Settings with GPG Passphrase

### Option A: Update the file in this project, then copy

1. Open `D:\DependencyCreator\settings.xml`
2. Find this line:
   ```xml
   <!-- <gpg.passphrase>YOUR_GPG_PASSPHRASE</gpg.passphrase> -->
   ```
3. Uncomment and replace with your passphrase:
   ```xml
   <gpg.passphrase>MySecurePass123!@#</gpg.passphrase>
   ```
4. Save the file
5. Copy to your Maven directory:
   ```bash
   copy D:\DependencyCreator\settings.xml %USERPROFILE%\.m2\settings.xml
   ```

### Option B: Edit directly in .m2 folder

1. Open `C:\Users\ShivamPaliwal\.m2\settings.xml`
2. Add your GPG passphrase to the `<properties>` section
3. Save the file

---

## Step 5: Verify Your Namespace on Sonatype Central

1. Go to https://central.sonatype.com/
2. Sign in (you should already be signed in since you got the token)
3. Click on your profile icon → **"Namespaces"**
4. Check what namespaces you have verified

**If you see a verified namespace (e.g., `io.github.yourname`):**
- Great! Note it down, you'll use it in the next step.

**If you DON'T have a verified namespace:**
- Click **"Add Namespace"**
- Enter: `io.github.YOUR_GITHUB_USERNAME` (instant verification for GitHub users)
- Or enter your custom domain (requires verification)

---

## Step 6: Update pom.xml with Your Information

Open `D:\DependencyCreator\pom.xml` and update:

### Line 7 - GroupId (MUST match your verified namespace):
```xml
<groupId>io.github.YOUR_USERNAME</groupId>
```
Example: `<groupId>io.github.shivampaliwal</groupId>`

### Line 13 - Project URL:
```xml
<url>https://github.com/YOUR_USERNAME/common-utils</url>
```

### Lines 28-32 - Developer Info:
```xml
<developer>
    <id>YOUR_USERNAME</id>
    <name>Shivam Paliwal</name>
    <email>your.email@example.com</email>
</developer>
```

### Lines 36-39 - Repository URLs:
```xml
<scm>
    <connection>scm:git:git://github.com/YOUR_USERNAME/common-utils.git</connection>
    <developerConnection>scm:git:ssh://github.com:YOUR_USERNAME/common-utils.git</developerConnection>
    <url>https://github.com/YOUR_USERNAME/common-utils</url>
</scm>
```

---

## Step 7: Update Package Names

Current package: `com.commonUtils`  
Need to change to: Your namespace (e.g., `io.github.yourname`)

### Files to update:

1. **Rename folder structure:**
   - From: `src/main/java/com/commonUtils/`
   - To: `src/main/java/io/github/yourname/`

2. **Update package declaration in ALL Java files:**
   - `src/main/java/.../config/UtilsAutoConfiguration.java`
   - `src/main/java/.../config/UtilsProperties.java`
   - `src/main/java/.../utils/StringUtils.java`
   - `src/main/java/.../utils/DateUtils.java`
   - `src/main/java/.../utils/JsonUtils.java`
   - `src/main/java/.../utils/CollectionUtils.java`

   Change first line from:
   ```java
   package com.commonUtils.config;
   ```
   To:
   ```java
   package io.github.yourname.config;
   ```

3. **Update UtilsAutoConfiguration.java (line 13):**
   ```java
   @ComponentScan(basePackages = "io.github.yourname")
   ```

4. **Update auto-configuration file:**
   - File: `src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`
   - Change from: `com.commonUtils.config.UtilsAutoConfiguration`
   - To: `io.github.yourname.config.UtilsAutoConfiguration`

---

## Step 8: Test Build

```bash
cd D:\DependencyCreator
mvn clean verify
```

**Expected output:**
```
[INFO] BUILD SUCCESS
```

**Check that these files exist in `target/` folder:**
- ✅ `common-utils-1.0.0.jar`
- ✅ `common-utils-1.0.0-sources.jar`
- ✅ `common-utils-1.0.0-javadoc.jar`
- ✅ All `.asc` signature files

---

## Step 9: Deploy to Maven Central! 🚀

```bash
mvn clean deploy
```

Or use the helper script:
```bash
deploy.bat
```

**What happens:**
1. Builds your library
2. Signs with GPG
3. Uploads to central.sonatype.com
4. Validates requirements
5. Auto-publishes to Maven Central

---

## Step 10: Monitor Deployment

1. Go to https://central.sonatype.com/publishing/deployments
2. Watch the status:
   - **VALIDATING** → Checking requirements
   - **VALIDATED** → Passed checks
   - **PUBLISHING** → Publishing to Maven Central
   - **PUBLISHED** → ✅ **SUCCESS!**

⏱️ **Typical time: 15-30 minutes**

---

## Step 11: Verify on Maven Central

After it shows "PUBLISHED":

1. Search at https://central.sonatype.com/
2. Search for: `io.github.yourname:common-utils`
3. Your library should appear!

**Use in any project:**
```xml
<dependency>
    <groupId>io.github.yourname</groupId>
    <artifactId>common-utils</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

## ✅ Quick Checklist

- [ ] Generated GPG key (`gpg --gen-key`)
- [ ] Published GPG key to key servers
- [ ] Updated `settings.xml` with GPG passphrase
- [ ] Copied `settings.xml` to `%USERPROFILE%\.m2\`
- [ ] Verified namespace on central.sonatype.com
- [ ] Updated `pom.xml` with your groupId, name, email, URLs
- [ ] Renamed packages from `com.commonUtils` to your namespace
- [ ] Updated all Java files with new package name
- [ ] Updated `@ComponentScan` in UtilsAutoConfiguration.java
- [ ] Updated auto-configuration imports file
- [ ] `mvn clean verify` succeeds
- [ ] `mvn clean deploy` completed
- [ ] Status shows PUBLISHED on central.sonatype.com

---

## Need Help?

If you encounter any errors, check:
- GPG passphrase is correct in settings.xml
- Namespace in pom.xml matches verified namespace on central.sonatype.com
- All package names are updated consistently
- All required POM fields are filled

**Ready to start? Begin with Step 1!** 🚀
