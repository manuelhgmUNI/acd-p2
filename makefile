comp_all:
	javac -cp ".:lib/*" -d bin src/*.java
exec_Main_grafo:
	java -cp "bin:lib/*" Main_grafo
clear_dotclass:
	rm bin/*.class