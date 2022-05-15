#!/usr/bin/env bash

######################################################
# 기존 nginx에 연결되어 있지 않지만, 실행 중이던 스프링 부트 종료
######################################################

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)  # 현재 stop.sh 가 속해 있는 경로 확인
source ${ABSDIR}/profile.sh # 일종의 import 구문. 따라서 profile.sh 의 여러 function을 사용할 수 있게 됨

IDLE_PORT=$(find_idle_port)

echo "> $IDLE_PORT 에서 구동 중인 애플리케이션 pid 확인"
IDLE_PID=$(lsof -ti tcp:${IDLE_PORT})

if [ -z ${IDLE_PID} ]
then
  echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $IDLE_PID"
  kill -15 ${IDLE_PID}
  sleep 5
fi