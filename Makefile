jar: src/meme/*.clj src/resources/*
	lein uberjar

archive: jar target/*.jar README.md
	$(eval VERSION := v$(shell ls target/*.jar | head -n 1 | sed -r 's@target/[^-]+-(.*)-SNAPSHOT.*@\1@g'))
	$(eval RELEASE_DIR := release/meme-$(VERSION))
	mkdir -p $(RELEASE_DIR)
	cp target/*.jar $(RELEASE_DIR)/
	cp README.md $(RELEASE_DIR)/
	tar czf $(RELEASE_DIR).tar.gz -C release meme-$(VERSION)

release: archive
	ghr $(VERSION) $(RELEASE_DIR)/

.PHONY: jar archive release
