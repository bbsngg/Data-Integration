cd ~/Data-Integration
git pull
cd ~/Data-Integration/QASystemOnStockKG
kill `lsof -ti :8100`
nohup /home/shkb/miniconda3/bin/python3.7 chatbot_server.py &