jar: src/meme/*.clj src/resources/* test
	lein uberjar

test: src/meme/*.clj src/resources/*
	lein test

archive: jar README.md
	$(eval VERSION := v$(shell ls target/*.jar | head -n 1 | sed -r 's@target/[^-]+-(.*)-SNAPSHOT.*@\1@g'))
	$(eval VERSION_DIR := release/$(VERSION))
	$(eval RELEASE_DIR := $(VERSION_DIR)/meme-$(VERSION))
	mkdir -p $(RELEASE_DIR)
	cp target/*.jar $(RELEASE_DIR)/
	cp README.md $(RELEASE_DIR)/
	tar czf $(RELEASE_DIR).tar.gz -C $(VERSION_DIR) meme-$(VERSION)

release: archive
	ghr $(VERSION) $(VERSION_DIR)

.PHONY: jar archive release test
