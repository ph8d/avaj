find . -name *.java > sources.txt
javac -sourcepath src @sources.txt