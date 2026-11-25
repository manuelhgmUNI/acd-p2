comp_all:
	javac -cp ".:lib/*" -d bin src/*.java
exec_Main_grafo:
	java -cp "bin:lib/*" Main_grafo
exec_test:
	java -cp "bin:lib/*" Main_grafo 1
clear_dotclass:
	rm bin/*.class