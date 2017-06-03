mvn clean assembly:assembly
if [ $? -eq 0 ]; then
	cp target/traveling-problem-1.0.0-jar-with-dependencies.jar traveling-problem.jar
	echo "Success"
	echo "Use: ./run.sh"
else
	echo "Build error"
fi
