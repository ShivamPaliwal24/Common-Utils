# Deploy Your Library NOW! 🚀

## ✅ All Configuration Complete!

I've successfully updated:
- ✅ **pom.xml** → GroupId: `io.github.shivampaliwal24`
- ✅ **Developer info** → Shivam Paliwal, shivam.paliwal74484@gmail.com
- ✅ **SCM URLs** → github.com/shivampaliwal24/common-utils
- ✅ **All Java files** → Package: `io.github.shivampaliwal24`
- ✅ **Auto-configuration** → Updated to new package
- ✅ **GPG keys** → Generated and published to key servers

---

## 🔑 One More Thing Needed: GPG Passphrase

I need your GPG passphrase (the one you created during `gpg --gen-key`) to update `settings.xml`.

**Option 1: Tell me your passphrase**
- I'll update `settings.xml` automatically for you

**Option 2: Update it manually**
1. Open: `D:\DependencyCreator\settings.xml`
2. Find line 24: `<!-- <gpg.passphrase>YOUR_GPG_PASSPHRASE</gpg.passphrase> -->`
3. Replace with: `<gpg.passphrase>YourActualPassphrase</gpg.passphrase>`
4. Save the file

---

## 📋 Steps to Deploy (After Adding GPG Passphrase):

### Step 1: Copy settings.xml to Maven Directory

```powershell
Copy-Item D:\DependencyCreator\settings.xml $env:USERPROFILE\.m2\settings.xml
```

### Step 2: Remove Old Package Directory

```powershell
Remove-Item -Recurse -Force "D:\DependencyCreator\src\main\java\com"
```

### Step 3: Verify Build

```powershell
cd D:\DependencyCreator
mvn clean verify
```

**Expected output:**
```
[INFO] BUILD SUCCESS
```

**Check `target/` folder for:**
- ✅ `common-utils-1.0.0.jar`
- ✅ `common-utils-1.0.0-sources.jar`
- ✅ `common-utils-1.0.0-javadoc.jar`
- ✅ All `.asc` signature files

### Step 4: Deploy to Maven Central! 🚀

```powershell
mvn clean deploy
```

This will:
1. Build your library
2. Run tests
3. Create all JARs
4. Sign with GPG
5. Upload to central.sonatype.com
6. Auto-publish to Maven Central

**Time: ~5-10 minutes for upload, 15-30 minutes for publication**

### Step 5: Monitor Deployment

1. Go to: https://central.sonatype.com/publishing/deployments
2. Watch status:
   - **VALIDATING** → Checking (2-5 min)
   - **VALIDATED** → Passed
   - **PUBLISHING** → Publishing (5-10 min)
   - **PUBLISHED** → ✅ **LIVE ON MAVEN CENTRAL!**

---

## 🎉 After Publication

Your library will be available at:
```
https://central.sonatype.com/artifact/io.github.shivampaliwal24/common-utils
```

Anyone can use it:
```xml
<dependency>
    <groupId>io.github.shivampaliwal24</groupId>
    <artifactId>common-utils</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

## 🆘 If You Get Errors

### GPG Signing Error
- Make sure GPG passphrase is correct in `settings.xml`
- Test GPG: `& "C:\Program Files\GnuPG\bin\gpg.exe" --list-secret-keys`

### Authentication Error
- Verify `settings.xml` is copied to `%USERPROFILE%\.m2\`
- Check that serverId is `<id>central</id>`

### Validation Error
- All required POM fields are filled (they are!)
- Namespace matches verified namespace on central.sonatype.com

---

## 📝 Quick Command Summary

```powershell
# 1. Copy settings.xml (after adding GPG passphrase)
Copy-Item D:\DependencyCreator\settings.xml $env:USERPROFILE\.m2\settings.xml

# 2. Remove old package
Remove-Item -Recurse -Force "D:\DependencyCreator\src\main\java\com"

# 3. Test build
cd D:\DependencyCreator
mvn clean verify

# 4. Deploy
mvn clean deploy

# 5. Check status
# Visit: https://central.sonatype.com/publishing/deployments
```

---

## 🎯 Summary

**What's Done:**
- ✅ Namespace: `io.github.shivampaliwal24`
- ✅ All files updated with correct package
- ✅ GPG keys generated and published
- ✅ Sonatype token configured

**What You Need:**
- ⏳ Add GPG passphrase to `settings.xml`
- ⏳ Run 4 simple commands
- ⏳ Wait 30 minutes

**Then:**
- 🎉 Your library is live on Maven Central!
- 🎉 Used by developers worldwide!

---

**Tell me your GPG passphrase or update `settings.xml` manually, then run the commands above!**

You're literally 5 minutes away from publishing to Maven Central! 🚀
