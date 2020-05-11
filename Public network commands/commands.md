<h2>2020-5-11</h2>

```
dev环境：
192.168.10.21pumper部署：
1.部署及构建链接：http://jop-dev.joowing.com/jop/automation/dev01/deployments
2.查看pumper执行状态：kubectl get pods -ndev01 | grep pumper
3.登入pumper：kube pumper-web
4.构建完毕后，用此命令执行：pumperctl -c start-day-pumping
5.tail -f pumper.log | grep -r "ERROR"

公网环境:
1.kubectl get pods -nidcquanhua | grep pumper
然后会显示
    pumper-console-6bf458bdb-4dl4r                    1/1     Running   0          43h
    pumper-convergence-primary-7bc8c8cdd4-2bt99       1/1     Running   0          43h
    pumper-web-b5f4b4494-lgkbj                        1/1     Running   0          43h
2.kubectl exec -it pumper-web-b5f4b4494-lgkbj -nidcquanhua bash
3.查看日志

将公网日志传到本机：
1.将日志传输到222.73.36.230的家目录
scp -P2002 ./pumper.log jian.li@222.73.36.230:~
2.用SecureCRT连接到222.73.36.230
sz pumper.log
将文件传送到本地(地址在SecureCRT中设置,Properties -> X/Y/Zmodem)
3.将pumper.log的ERROR grep出来
cat ./pumper.log | grep -r "ERROR" > /Users/lijian/Desktop/pumper.error.log
4.用脚本对pumper.error.log去重
```

