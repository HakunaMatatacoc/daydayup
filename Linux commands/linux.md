- 查看指定端口的进程
sudo lsof -i :27017
或者
netstat -anp | grep 80

- 根据进程名称
ps -ef | grep nginx

- 根据PID杀进程
sudo kill -9 859



