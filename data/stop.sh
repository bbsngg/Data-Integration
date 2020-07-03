ps -ef|grep data-0.0.1-SNAPSHOT.jar | grep -v grep |awk '{print $2}'|while read pid
    do
       kill -9 $pid
       echo " $pid kill"
    done