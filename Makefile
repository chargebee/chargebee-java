.PHONY: update-version increment-major increment-minor increment-patch test build clean install

update-version:
	@echo "$(VERSION)" > VERSION
	@perl -i -pe 'if (!$$done && /<version>[.\-\d\w]+<\/version>/ && $$. < 20) { s|<version>[.\-\d\w]+</version>|<version>$(VERSION)</version>|; $$done = 1; }' pom.xml
	@perl -pi -e 's|LIBRARY_VERSION = "[.\-\d\w]+"|LIBRARY_VERSION = "$(VERSION)"|' src/main/java/com/chargebee/Environment.java

increment-major:
	$(eval CURRENT := $(shell cat VERSION))
	$(eval MAJOR := $(shell echo $(CURRENT) | cut -d. -f1))
	$(eval NEW_VERSION := $(shell echo $$(($(MAJOR) + 1)).0.0))
	@$(MAKE) update-version VERSION=$(NEW_VERSION)
	@echo "Version bumped from $(CURRENT) to $(NEW_VERSION)"

increment-minor:
	$(eval CURRENT := $(shell cat VERSION))
	$(eval MAJOR := $(shell echo $(CURRENT) | cut -d. -f1))
	$(eval MINOR := $(shell echo $(CURRENT) | cut -d. -f2))
	$(eval NEW_VERSION := $(MAJOR).$(shell echo $$(($(MINOR) + 1))).0)
	@$(MAKE) update-version VERSION=$(NEW_VERSION)
	@echo "Version bumped from $(CURRENT) to $(NEW_VERSION)"

increment-patch:
	$(eval CURRENT := $(shell cat VERSION))
	$(eval MAJOR := $(shell echo $(CURRENT) | cut -d. -f1))
	$(eval MINOR := $(shell echo $(CURRENT) | cut -d. -f2))
	$(eval PATCH := $(shell echo $(CURRENT) | cut -d. -f3))
	$(eval NEW_VERSION := $(MAJOR).$(MINOR).$(shell echo $$(($(PATCH) + 1))))
	@$(MAKE) update-version VERSION=$(NEW_VERSION)
	@echo "Version bumped from $(CURRENT) to $(NEW_VERSION)"

test:
	mvn test

build:
	mvn clean package

clean:
	mvn clean

install:
	mvn clean install