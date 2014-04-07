# Programs
RM 	= rm
ECHO = /bin/echo
JAVAC = javac
JAVA = java
JAVADOC = javadoc 
TOUCH = touch
JAR = jar

# Project Paths
PROJECT_ROOT=$(CURDIR)
SRCDIR=$(PROJECT_ROOT)/src
PACKAGE=$(SRCDIR)/cs296JTalk2

.PHONY: all compile clean

all: setup compile

setup:
	@@$(ECHO) "Setting up compilation..."
	@mkdir -p doc

compile:
	@$(RM) -rf MANIFEST.MF
	@mkdir -p bin
	@$(JAVAC) -cp src/ -d bin src/jtalkG02.java
	@$(TOUCH) MANIFEST.MF
	@cd bin;\
	$(ECHO) "Main-Class: jtalkG02" > ../MANIFEST.MF;\
	$(JAR) -cvmf ../MANIFEST.MF ../jtalk.jar jtalkG02.class cs296JTalk2

clean:
	@$(RM) -rf bin
	@$(RM) -rf doc MANIFEST.MF jtalk.jar
	
