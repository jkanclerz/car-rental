help:
	@echo "Please use \`make <target>' where <target> is one of"
	@echo "  test                       to perform unit tests"
	@echo "  compile                    compile project      "


test:
	mvn test-compile
	mvn test


