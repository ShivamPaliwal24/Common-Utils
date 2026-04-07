# Deployment Guide to Maven Central via Sonatype

This guide will walk you through the process of publishing this library to Maven Central through Sonatype OSSRH.

## Prerequisites

Before you can publish to Maven Central, you need:

1. A Sonatype JIRA account
2. An approved namespace (groupId)
3. GPG keys for signing artifacts
4. Maven installed on your system

## Step 1: Create a Sonatype JIRA Account

1. Go to https://issues.sonatype.org/
2. Click "Sign up" to create a new account
3. Verify your email address

## Step 2: Request a New Namespace

1. Log in to Sonatype JIRA
2. Create a new issue with the following details:
   - **Project**: Community Support - Open Source Project Repository Hosting (OSSRH)
   - **Issue Type**: New Project
   - **Summary**: Request for namespace com.commonUtils (use your actual domain/namespace)
   - **Group Id**: com.commonUtils (your desired groupId)
   - **Project URL**: Your GitHub repository URL
   - **SCM URL**: Your GitHub repository URL with .git extension
   - **Username**: Your Sonatype username

3. Wait for approval (usually takes 1-2 business days)
4. You may need to prove domain ownership by:
   - Creating a DNS TXT record, OR
   - Creating a repository under your GitHub account matching the groupId

## Step 3: Generate GPG Keys

GPG (GNU Privacy Guard) is used to sign your artifacts, which is required for Maven Central.

### Install GPG

**Windows:**
```bash
# Download and install from: https://www.gnupg.org/download/
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
sudo yum install gnupg      # CentOS/RHEL
```

### Generate a Key Pair

```bash
# Generate a new GPG key
gpg --gen-key

# Follow the prompts:
# - Choose RSA and RSA (default)
# - Key size: 2048 or 4096 bits
# - Expiration: 0 (never expires) or set an expiration
# - Enter your name and email
# - Create a strong passphrase (REMEMBER THIS!)
```

### Distribute Your Public Key

```bash
# List your keys to find the key ID
gpg --list-keys

# You'll see output like:
# pub   rsa2048 2024-01-01 [SC]
#       ABCDEF1234567890ABCDEF1234567890ABCDEF12
# uid   [ultimate] Your Name <your.email@example.com>

# Upload your public key to key servers (use the long key ID)
gpg --keyserver keyserver.ubuntu.com --send-keys ABCDEF1234567890ABCDEF1234567890ABCDEF12
gpg --keyserver keys.openpgp.org --send-keys ABCDEF1234567890ABCDEF1234567890ABCDEF12
```

## Step 4: Configure Maven Settings

1. Copy `settings.xml.template` to `~/.m2/settings.xml`
   - Windows: `C:\Users\YourName\.m2\settings.xml`
   - Mac/Linux: `~/.m2/settings.xml`

2. Update the following values:
   - `YOUR_SONATYPE_USERNAME`: Your Sonatype JIRA username
   - `YOUR_SONATYPE_PASSWORD`: Your Sonatype JIRA password
   - `YOUR_GPG_PASSPHRASE`: The passphrase you created for your GPG key

## Step 5: Update pom.xml

Before deploying, update these values in `pom.xml`:

```xml

<groupId>com.commonUtilscom.commonUtils</groupId>  <!-- Your approved namespace -->
<artifactId>common-utils</artifactId>
<version>1.0.0</version>

<name>Common Utils Library</name>
<description>Your library description</description>
<url>https://github.com/yourusername/common-utils</url>

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
<url>https://github.com/yourusername/common-utils/tree/main</url>
</scm>
```

## Step 6: Build and Deploy

### Test the Build Locally

```bash
# Clean and build the project
mvn clean install

# This should create:
# - The main JAR
# - Sources JAR
# - Javadoc JAR
# - All JARs should be signed with .asc files
```

### Deploy to Sonatype

```bash
# For SNAPSHOT versions (ends with -SNAPSHOT)
mvn clean deploy

# For release versions
mvn clean deploy -P release
```

### Troubleshooting GPG Signing

If you encounter GPG signing issues:

```bash
# Test GPG signing manually
gpg --armor --detach-sign pom.xml

# If it fails, make sure GPG agent is running
gpgconf --launch gpg-agent

# On Windows, you might need to set the full path
# Add to pom.xml or settings.xml:
<gpg.executable>C:\Program Files (x86)\GnuPG\bin\gpg.exe</gpg.executable>
```

## Step 7: Release on Sonatype

After successful deployment:

1. Log in to https://s01.oss.sonatype.org/ (or https://oss.sonatype.org/ for older accounts)
2. Click on "Staging Repositories" in the left menu
3. Find your repository (usually named like `comyourcompany-XXXX`)
4. Select it and click "Close" button
5. Wait for the validation to complete (usually a few minutes)
6. If validation passes, click "Release" button
7. Your artifact will be synced to Maven Central within 10-30 minutes
8. Full availability on Maven Central Search can take up to 2 hours

## Step 8: Verify Publication

After release, verify your artifact:

1. Check on Maven Central: https://search.maven.org/
2. Search for: `g:com.commonUtils a:common-utils`
3. Your artifact should appear within 2 hours

## Using the Released Library

Once published, others can use your library by adding it to their `pom.xml`:

```xml

<dependency>
    <groupId>com.commonUtilscom.commonUtils</groupId>
    <artifactId>common-utils</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Releasing New Versions

For subsequent releases:

1. Update the version in `pom.xml`
2. Update the CHANGELOG in README.md
3. Commit and tag the release:
   ```bash
   git tag -a v1.0.1 -m "Release version 1.0.1"
   git push origin v1.0.1
   ```
4. Deploy again: `mvn clean deploy`
5. Follow Step 7 to release on Sonatype

## Automated Release with Maven Release Plugin

Alternatively, use the Maven Release Plugin:

```bash
# Prepare the release
mvn release:prepare

# Perform the release
mvn release:perform
```

This will:
- Update version numbers
- Create a Git tag
- Deploy to Sonatype
- Prepare for the next development version

## Common Issues and Solutions

### Issue: GPG signing fails
**Solution**: Ensure GPG is installed and your key is properly configured. Test with `gpg --list-keys`

### Issue: Authentication fails
**Solution**: Double-check your Sonatype credentials in `~/.m2/settings.xml`

### Issue: Validation fails on Sonatype
**Solution**: Check the validation messages. Common issues:
- Missing Javadoc JAR
- Missing Sources JAR
- Invalid POM (missing required fields)
- Unsigned artifacts

### Issue: Can't find the staging repository
**Solution**: Check if you're using the correct Sonatype URL (s01.oss.sonatype.org vs oss.sonatype.org)

## Resources

- [Sonatype OSSRH Guide](https://central.sonatype.org/publish/publish-guide/)
- [GPG Guide](https://central.sonatype.org/publish/requirements/gpg/)
- [Maven Deploy Plugin](https://maven.apache.org/plugins/maven-deploy-plugin/)
- [Nexus Staging Maven Plugin](https://github.com/sonatype/nexus-maven-plugins/tree/main/staging/maven-plugin)
