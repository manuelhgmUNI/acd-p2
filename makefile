comp_all:
	javac -cp ".:lib/*" -d bin *.java
exec_Main_grafo:
	java -cp "bin:lib/*" Main_grafo