# Your Deployment Steps to Maven Central

✅ **Token configured!** Your Sonatype Central token is already set up in `settings.xml`

## What You Need to Do Next

### Step 1: Copy Maven Settings to Your .m2 Folder

**Windows:**
```bash
# Copy the settings.xml to your Maven directory
copy settings.xml %USERPROFILE%\.m2\settings.xml
```

Or manually:
1. Open File Explorer
2. Navigate to `C:\Users\YourUsername\.m2\`
3. Copy `D:\DependencyCreator\settings.xml` to that folder
4. If `.m2` folder doesn't exist, create it first

### Step 2: Generate GPG Keys

GPG keys are required to sign your artifacts (Maven Central requirement).

#### Install GPG (if not already installed)

**Windows - Option 1 (Chocolatey):**
```bash
choco install gnupg
```

**Windows - Option 2 (Direct Download):**
- Download from: https://www.gnupg.org/download/
- Install Gpg4win

#### Generate Your GPG Key

```bash
# Generate a new GPG key pair
gpg --gen-key
```

Follow the prompts:
1. **Real name**: Your name
2. **Email**: Your email address
3. **Passphrase**: Create a STRONG passphrase (you'll need this later!)

**IMPORTANT: Remember your GPG passphrase!**

#### Publish Your Public Key

```bash
# List your keys to get the key ID
gpg --list-keys

# You'll see something like:
# pub   rsa3072 2024-01-01 [SC]
#       1234567890ABCDEF1234567890ABCDEF12345678
# uid   [ultimate] Your Name <your.email@example.com>

# Copy the 40-character key ID and publish it
gpg --keyserver keyserver.ubuntu.com --send-keys YOUR_KEY_ID
gpg --keyserver keys.openpgp.org --send-keys YOUR_KEY_ID
```

#### Update settings.xml with GPG Passphrase

After generating GPG keys, update your `%USERPROFILE%\.m2\settings.xml`:

```xml
<properties>
    <gpg.executable>gpg</gpg.executable>
    <gpg.passphrase>YOUR_GPG_PASSPHRASE_HERE</gpg.passphrase>
</properties>
```

### Step 3: Verify Your Namespace on Sonatype Central

1. Go to https://central.sonatype.com/
2. Sign in (you're already signed in if you got the token)
3. Click on your profile → **"Namespaces"**
4. Check if you have a verified namespace

**If you don't have a namespace:**
- Click **"Add Namespace"**
- Enter: `io.github.YOUR_GITHUB_USERNAME` (instant verification!)
- Or use your custom domain

### Step 4: Update pom.xml with Your Information

Open `pom.xml` and update these sections:

```xml
<!-- Line 7: Change to YOUR verified namespace -->
<groupId>io.github.YOUR_USERNAME</groupId>

<!-- Line 13: Update to YOUR project URL -->
<url>https://github.com/YOUR_USERNAME/common-utils</url>

<!-- Lines 28-32: Update with YOUR information -->
<developer>
    <id>YOUR_USERNAME</id>
    <name>Your Actual Name</name>
    <email>your.email@example.com</email>
</developer>

<!-- Lines 36-39: Update with YOUR repository URLs -->
<scm>
    <connection>scm:git:git://github.com/YOUR_USERNAME/common-utils.git</connection>
    <developerConnection>scm:git:ssh://github.com:YOUR_USERNAME/common-utils.git</developerConnection>
    <url>https://github.com/YOUR_USERNAME/common-utils</url>
</scm>
```

### Step 5: Update Package Names

The current package is `com.commonUtils` - you need to change it to match your namespace.

**Example:** If your namespace is `io.github.johndoe`, rename:
- From: `com.commonUtils`
- To: `io.github.johndoe`

**Files to update:**
1. Rename the folder: `src/main/java/com/commonUtils/` → `src/main/java/io/github/yourusername/`
2. Update package declaration in all Java files
3. Update `UtilsAutoConfiguration.java` line 13:
   ```java
   @ComponentScan(basePackages = "io.github.yourusername")
   ```
4. Update `src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`:
   ```
   io.github.yourusername.config.UtilsAutoConfiguration
   ```

### Step 6: Test Build

```bash
# Navigate to project folder
cd D:\DependencyCreator

# Clean and verify
mvn clean verify
```

**Expected output:**
```
[INFO] BUILD SUCCESS
[INFO] Total time: XX.XXX s
```

**Check that these files are created in `target/`:**
- ✅ `common-utils-1.0.0.jar`
- ✅ `common-utils-1.0.0-sources.jar`
- ✅ `common-utils-1.0.0-javadoc.jar`
- ✅ `*.asc` signature files for each JAR

### Step 7: Deploy to Maven Central

```bash
# Deploy
mvn clean deploy
```

**Or use the deployment script:**
```bash
deploy.bat
```

### Step 8: Monitor Deployment

1. Go to https://central.sonatype.com/publishing/deployments
2. You'll see your deployment with status:
   - **VALIDATING** → checking requirements
   - **VALIDATED** → passed validation
   - **PUBLISHING** → being published
   - **PUBLISHED** → ✅ Live on Maven Central!

⏱️ **Total time: 15-30 minutes**

### Step 9: Verify Publication

**Search on Maven Central:**
- https://central.sonatype.com/
- Search for your `groupId:artifactId`

**Test in another project:**
```xml
<dependency>
    <groupId>io.github.YOUR_USERNAME</groupId>
    <artifactId>common-utils</artifactId>
    <version>1.0.0</version>
</dependency>
```

## ✅ Quick Checklist

- [ ] Copied `settings.xml` to `%USERPROFILE%\.m2\`
- [ ] Generated GPG keys (`gpg --gen-key`)
- [ ] Published GPG public key to key servers
- [ ] Updated `settings.xml` with GPG passphrase
- [ ] Verified namespace on central.sonatype.com
- [ ] Updated `pom.xml` with your groupId, URLs, developer info
- [ ] Renamed packages from `com.commonUtils` to your namespace
- [ ] `mvn clean verify` succeeds
- [ ] `mvn clean deploy` completed
- [ ] Checked deployment status on central.sonatype.com

## 🆘 Need Help?

**If GPG commands don't work:**
```bash
# Check if GPG is installed
gpg --version

# If not found, install it first
```

**If build fails:**
```bash
# Check Java version (need 17+)
java -version

# Check Maven version (need 3.6+)
mvn -version
```

**If deployment fails:**
- Check error messages in console
- Verify all POM fields are filled
- Ensure GPG keys are generated and published
- Check that namespace matches in pom.xml and central.sonatype.com

## 📞 Support Resources

- **Sonatype Central Portal**: https://central.sonatype.com/
- **Your Deployments**: https://central.sonatype.com/publishing/deployments
- **Documentation**: https://central.sonatype.org/

---

**Your credentials are ready! Just complete the GPG setup and update pom.xml, then you can deploy!** 🚀
