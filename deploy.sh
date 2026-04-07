#!/bin/bash
# Deployment Script for Sonatype Central Publisher
# This script helps deploy your library to Maven Central

set -e

echo "======================================"
echo "  Maven Central Deployment Script"
echo "======================================"
echo ""

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed. Please install Maven first."
    exit 1
fi

# Check if GPG is installed
if ! command -v gpg &> /dev/null; then
    echo "❌ GPG is not installed. Please install GPG first."
    exit 1
fi

echo "✅ Maven found: $(mvn -version | head -n 1)"
echo "✅ GPG found: $(gpg --version | head -n 1)"
echo ""

# Check if settings.xml exists
if [ ! -f ~/.m2/settings.xml ]; then
    echo "⚠️  WARNING: ~/.m2/settings.xml not found!"
    echo "   Please copy settings.xml.template to ~/.m2/settings.xml"
    echo "   and configure your credentials."
    exit 1
fi

echo "✅ Maven settings found"
echo ""

# Check if GPG keys exist
if ! gpg --list-secret-keys | grep -q "sec"; then
    echo "⚠️  WARNING: No GPG secret keys found!"
    echo "   Please generate GPG keys first: gpg --gen-key"
    exit 1
fi

echo "✅ GPG keys found"
echo ""

# Ask for confirmation
echo "This will deploy the library to Maven Central."
read -p "Continue? (y/n) " -n 1 -r
echo ""

if [[ ! $REPLY =~ ^[Yy]$ ]]; then
    echo "Deployment cancelled."
    exit 0
fi

echo ""
echo "======================================"
echo "  Starting Deployment Process"
echo "======================================"
echo ""

# Step 1: Clean
echo "📦 Step 1/4: Cleaning previous builds..."
mvn clean

# Step 2: Verify
echo ""
echo "🔍 Step 2/4: Running tests and verification..."
mvn verify

# Step 3: Deploy
echo ""
echo "🚀 Step 3/4: Deploying to Maven Central..."
mvn deploy

# Step 4: Done
echo ""
echo "======================================"
echo "  ✅ Deployment Complete!"
echo "======================================"
echo ""
echo "Next steps:"
echo "1. Visit https://central.sonatype.com/publishing/deployments"
echo "2. Check the status of your deployment"
echo "3. Wait 15-30 minutes for it to appear on Maven Central"
echo "4. Search for your artifact at https://central.sonatype.com/"
echo ""
echo "Your artifact will be available at:"
echo "  https://central.sonatype.com/artifact/<groupId>/<artifactId>"
echo ""
