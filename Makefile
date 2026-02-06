.PHONY: update-version increment-major increment-minor increment-patch test build clean install check format

# Version file location
VERSION_FILE := VERSION
BUILD_GRADLE_FILE := build.gradle.kts

# Gradle commands
GRADLE := ./gradlew

update-version:
	@echo "$(VERSION)" > $(VERSION_FILE)
	@perl -pi -e 's|version = "[.\-\d\w]+"|version = "$(VERSION)"|' $(BUILD_GRADLE_FILE)
	@echo "Updated version to $(VERSION)"

increment-major:
	$(eval CURRENT := $(shell cat $(VERSION_FILE)))
	$(eval MAJOR := $(shell echo $(CURRENT) | cut -d. -f1))
	$(eval NEW_VERSION := $(shell echo $$(($(MAJOR) + 1)).0.0))
	@$(MAKE) update-version VERSION=$(NEW_VERSION)
	@echo "Version bumped from $(CURRENT) to $(NEW_VERSION)"

increment-minor:
	$(eval CURRENT := $(shell cat $(VERSION_FILE)))
	$(eval MAJOR := $(shell echo $(CURRENT) | cut -d. -f1))
	$(eval MINOR := $(shell echo $(CURRENT) | cut -d. -f2))
	$(eval NEW_VERSION := $(MAJOR).$(shell echo $$(($(MINOR) + 1))).0)
	@$(MAKE) update-version VERSION=$(NEW_VERSION)
	@echo "Version bumped from $(CURRENT) to $(NEW_VERSION)"

increment-patch:
	$(eval CURRENT := $(shell cat $(VERSION_FILE)))
	$(eval MAJOR := $(shell echo $(CURRENT) | cut -d. -f1))
	$(eval MINOR := $(shell echo $(CURRENT) | cut -d. -f2))
	$(eval PATCH := $(shell echo $(CURRENT) | cut -d. -f3))
	$(eval NEW_VERSION := $(MAJOR).$(MINOR).$(shell echo $$(($(PATCH) + 1))))
	@$(MAKE) update-version VERSION=$(NEW_VERSION)
	@echo "Version bumped from $(CURRENT) to $(NEW_VERSION)"

install:
	@echo "Installing dependencies..."
	@$(GRADLE) dependencies

test:
	@echo "Running tests..."
	@$(GRADLE) test

test-coverage:
	@echo "Running tests with coverage..."
	@$(GRADLE) test jacocoTestReport
	@echo "Coverage report generated in build/reports/jacoco/test/html/index.html"

security-check:
	@echo "Running OWASP dependency check..."
	@$(GRADLE) dependencyCheckAnalyze

check: test
	@echo "All checks passed!"

build: clean
	@echo "Building project..."
	@$(GRADLE) build

clean:
	@echo "Cleaning build artifacts..."
	@$(GRADLE) clean
	@rm -rf build/
	@rm -rf dist/
	@rm -rf target/
	@find . -type f -name '.DS_Store' -delete

javadoc:
	@echo "Generating Javadoc..."
	@$(GRADLE) javadoc

jar:
	@echo "Building JAR..."
	@$(GRADLE) jar

assemble:
	@echo "Assembling artifacts..."
	@$(GRADLE) assemble

update:
	@echo "Updating dependencies..."
	@$(GRADLE) dependencies --refresh-dependencies

outdated:
	@echo "Checking for outdated dependencies..."
	@$(GRADLE) dependencyUpdates

format: 
	@echo "Format is already been implemented by the SDK generator for this module"
