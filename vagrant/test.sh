NUM=$(sudo cat ~/.bash_profile | grep -q java | wc -c)
if [ $NUM -eq 0 ]; then
	echo ZERO
else
	echo $NUM
fi
echo done
