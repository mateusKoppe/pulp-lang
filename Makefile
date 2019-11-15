rar: compile
	cd source && jar -cmvf manifest.txt ../Pulp.jar *.class **/*.class **/**/*.class

compile:
	cd source && javac *.java && javac **/*.java && javac **/**/*.java

clean:
	cd source && rm *.class && rm **/*.class && rm **/**/*.class
