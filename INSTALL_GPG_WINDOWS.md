# Install GPG on Windows - Step by Step

GPG (GNU Privacy Guard) is required to sign your artifacts before publishing to Maven Central.

## Quick Installation Guide

### Method 1: Download and Install Gpg4win (Recommended)

1. **Download Gpg4win:**
   - Go to: https://www.gpg4win.org/download.html
   - Click the green **"Download Gpg4win"** button
   - Save the installer file (e.g., `gpg4win-4.x.x.exe`)

2. **Install Gpg4win:**
   - Run the downloaded `.exe` file
   - Click **"Next"** through the installer
   - You can deselect components like "GPA" and "Kleopatra" if you only need GPG command line
   - Keep **"GnuPG"** selected (this is essential!)
   - Click **"Install"**
   - Click **"Finish"**

3. **Verify Installation:**
   Open a **new** PowerShell or Command Prompt window:
   ```bash
   gpg --version
   ```
   
   You should see:
   ```
   gpg (GnuPG) 2.x.x
   ```

### Method 2: Using Winget (Windows Package Manager)

If you have Windows 11 or Windows 10 with winget:

```bash
winget install GnuPG.Gpg4win
```

## After Installation: Generate Your GPG Keys

### Step 1: Generate Key Pair

```bash
gpg --gen-key
```

You'll be prompted for:

1. **Real name**: Enter your name (e.g., "John Doe")
2. **Email address**: Enter your email (e.g., "john@example.com")
3. **Passphrase**: Create a STRONG passphrase
   - ⚠️ **IMPORTANT**: Remember this passphrase! You'll need it for deployment
   - Use at least 12 characters with letters, numbers, and symbols

### Step 2: List Your Keys

```bash
gpg --list-keys
```

Output will look like:
```
pub   rsa3072 2024-01-15 [SC] [expires: 2026-01-15]
      1234567890ABCDEF1234567890ABCDEF12345678
uid           [ultimate] Your Name <your.email@example.com>
sub   rsa3072 2024-01-15 [E] [expires: 2026-01-15]
```

Copy the **40-character key ID** (the long hexadecimal string).

### Step 3: Publish Your Public Key

Replace `YOUR_KEY_ID` with your actual 40-character key ID:

```bash
gpg --keyserver keyserver.ubuntu.com --send-keys YOUR_KEY_ID
gpg --keyserver keys.openpgp.org --send-keys YOUR_KEY_ID
```

Example:
```bash
gpg --keyserver keyserver.ubuntu.com --send-keys 1234567890ABCDEF1234567890ABCDEF12345678
gpg --keyserver keys.openpgp.org --send-keys 1234567890ABCDEF1234567890ABCDEF12345678
```

### Step 4: Update Maven Settings

Edit `C:\Users\YourUsername\.m2\settings.xml` and add your GPG passphrase:

```xml
<properties>
    <gpg.executable>gpg</gpg.executable>
    <gpg.passphrase>YOUR_GPG_PASSPHRASE_HERE</gpg.passphrase>
</properties>
```

Replace `YOUR_GPG_PASSPHRASE_HERE` with the passphrase you created in Step 1.

## Complete Setup Checklist

After installing GPG:

- [ ] GPG installed (`gpg --version` works)
- [ ] Generated key pair (`gpg --gen-key`)
- [ ] Noted down your GPG passphrase (securely!)
- [ ] Listed keys and copied key ID (`gpg --list-keys`)
- [ ] Published public key to keyserver.ubuntu.com
- [ ] Published public key to keys.openpgp.org
- [ ] Updated `%USERPROFILE%\.m2\settings.xml` with GPG passphrase
- [ ] Copied `D:\DependencyCreator\settings.xml` to `%USERPROFILE%\.m2\settings.xml`

## Next Steps

Once GPG is set up, you can proceed with deployment:

1. Update `pom.xml` with your information (see `YOUR_DEPLOYMENT_STEPS.md`)
2. Update package names from `com.commonUtils` to your namespace
3. Run `mvn clean verify` to test
4. Run `mvn clean deploy` to publish to Maven Central

## Troubleshooting

### Issue: GPG command not found after installation

**Solution:** 
- Close and reopen your terminal/PowerShell
- The PATH needs to be refreshed
- If still not working, manually add to PATH: `C:\Program Files (x86)\GnuPG\bin`

### Issue: GPG asks for passphrase every time

**Solution:**
- This is normal during `mvn deploy`
- Make sure passphrase is in `settings.xml` to avoid manual entry
- Or use GPG agent for caching

### Issue: Can't publish to key server

**Solution:**
```bash
# Try alternative key servers
gpg --keyserver hkp://keyserver.ubuntu.com --send-keys YOUR_KEY_ID
gpg --keyserver pgp.mit.edu --send-keys YOUR_KEY_ID
```

### Issue: Forgot GPG passphrase

**Solution:**
- You'll need to generate a new key pair
- Delete old keys: `gpg --delete-secret-keys YOUR_KEY_ID`
- Generate new: `gpg --gen-key`

## Quick Reference Commands

```bash
# List all keys
gpg --list-keys

# List secret keys
gpg --list-secret-keys

# Test signing (to verify GPG works)
gpg --armor --detach-sign pom.xml

# Delete a key (if needed)
gpg --delete-secret-keys KEY_ID
gpg --delete-keys KEY_ID
```

## Download Link

**Gpg4win Download Page:**
https://www.gpg4win.org/download.html

---

After completing this setup, follow the instructions in **YOUR_DEPLOYMENT_STEPS.md** to deploy your library!
