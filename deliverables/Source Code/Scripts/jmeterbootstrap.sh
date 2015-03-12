
echo "deleting all the time"
sudo rm -f /etc/localtime
echo "linking real time"
sudo ln -s /usr/share/zoneinfo/America/Los_Angeles /etc/localtime
echo "Today is:"
date
