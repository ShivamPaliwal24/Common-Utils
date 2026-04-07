# Final Steps to Deploy - Almost There! 🚀

## ✅ Completed Steps:

1. ✅ **GPG Keys Generated**
   - Key ID: `6E3F4D00FFA67B6B457A7F511026B81AF2F36397`
   - Name: shivam paliwal
   - Email: shivam.paliwal74484@gmail.com

2. ✅ **GPG Keys Published to Key Servers**
   - keyserver.ubuntu.com ✅
   - keys.openpgp.org ✅

3. ✅ **Sonatype Token Configured** in settings.xml

---

## 🎯 Remaining Steps (Quick & Easy):

### Step 1: Add GPG Passphrase to settings.xml

You need to add the passphrase you created when generating your GPG key.

**Option A: I'll help you update the file**
- Just tell me your GPG passphrase (the one you entered during `gpg --gen-key`)
- I'll update the settings.xml file for you

**Option B: Update it manually**
1. Open `D:\DependencyCreator\settings.xml`
2. Find line ~24 with: `<!-- <gpg.passphrase>YOUR_GPG_PASSPHRASE</gpg.passphrase> -->`
3. Replace it with: `<gpg.passphrase>YourActualPassphrase</gpg.passphrase>`
4. Save the file

---

### Step 2: Copy settings.xml to Maven Directory

Run this command:
```powershell
Copy-Item D:\DependencyCreator\settings.xml $env:USERPROFILE\.m2\settings.xml
```

Or manually copy `D:\DependencyCreator\settings.xml` to `C:\Users\ShivamPaliwal\.m2\settings.xml`

---

### Step 3: Verify Your Namespace on Sonatype Central

1. Go to https://central.sonatype.com/
2. Sign in
3. Click your profile → **"Namespaces"**
4. Check what namespace you have

**Do you have a GitHub account?**
- If YES: Use `io.github.YOUR_GITHUB_USERNAME` (instant verification!)
- If NO: You can use a custom domain (requires verification)

**Important:** Write down your verified namespace - you'll need it for the next step!

---

### Step 4: Update pom.xml with Your Information

I need to know your **verified namespace** from Step 3 to help you update pom.xml correctly.

**What to update:**
1. `<groupId>` - Must match your verified namespace
2. `<name>` and `<email>` - Already have: shivam paliwal, shivam.paliwal74484@gmail.com
3. `<url>` and `<scm>` - Your GitHub/repository URLs

**Tell me your GitHub username or verified namespace, and I'll update pom.xml for you!**

---

### Step 5: Update Package Names

Once we update pom.xml, you'll need to rename packages from `com.commonUtils` to your namespace.

**Example:** If your namespace is `io.github.shivampaliwal`:
- Rename folder: `src/main/java/com/commonUtils/` → `src/main/java/io/github/shivampaliwal/`
- Update package declarations in all Java files
- Update `@ComponentScan` and auto-configuration files

**I can help you with the exact commands once you tell me your namespace!**

---

### Step 6: Build and Deploy

```powershell
# Test build
mvn clean verify

# Deploy to Maven Central
mvn clean deploy
```

---

## 🔑 Information I Need from You:

To complete the setup, please tell me:

1. **Your GPG passphrase** (so I can update settings.xml)
   - OR you can update it manually in `settings.xml` line ~24

2. **Your GitHub username** (if you have one)
   - This will be your namespace: `io.github.YOUR_USERNAME`
   - OR tell me what namespace you verified on central.sonatype.com

Once I have this information, I'll:
- ✅ Update settings.xml with GPG passphrase
- ✅ Update pom.xml with your namespace and info
- ✅ Give you exact commands to rename packages
- ✅ Get you ready to deploy in minutes!

---

## 📝 Quick Commands for PowerShell

**Note:** Since GPG needs full path in PowerShell, here are the correct commands:

### List GPG keys:
```powershell
& "C:\Program Files\GnuPG\bin\gpg.exe" --list-keys
```

### Publish GPG key (already done!):
```powershell
& "C:\Program Files\GnuPG\bin\gpg.exe" --keyserver keyserver.ubuntu.com --send-keys 6E3F4D00FFA67B6B457A7F511026B81AF2F36397
```

### Copy settings.xml:
```powershell
Copy-Item D:\DependencyCreator\settings.xml $env:USERPROFILE\.m2\settings.xml
```

---

## ❓ What to Do Now:

1. **Either manually update `settings.xml` with your GPG passphrase**
   - OR tell me your passphrase and I'll update it

2. **Tell me your GitHub username or verified namespace from central.sonatype.com**

3. **Then I'll prepare everything and you can deploy in 5 minutes!** 🚀

---

**You're so close! Just 2 pieces of information needed!**
