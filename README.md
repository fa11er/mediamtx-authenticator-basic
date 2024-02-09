# mediamtx-authenticator-basic

Used as an external authentication  server for Mediamtx (https://github.com/bluenviron/mediamtx?tab=readme-ov-file#authentication)
## Getting Started

mediamtx-authenticator-basic is available on [Github](https://github.com/fa11er/mediamtx-authenticator-basic) in source code format.
## Additional Help

If you need assistance, you can contact fa11er.ad@gmail.com
## Installation

### Requirements
- JAVA 17+

- Redis
### Build from source
*mediamtx-authenticator-basic uses [Maven](https://maven.apache.org/) for its build system.*

Step 1: Download [mediamtx-authenticator-basic source]()

Step 2: To unzip archive, run:
```
unzip mediamtx-authenticator-basic-master.zip
```
Step 3: To go to project directory, run:
```
cd mediamtx-authenticator-basic-master/
```
Step 4: To build .jar package to ./target/mediamtx-authenticator-basic, run:
```
./mvnw -Dmaven.test.skip.exec clean package && ./mvnw validate
```
Step 5: To edit configuration file (add correct database authorization data), run:
```
mcedit ./target/mediamtx-authenticator-basic/application.properties
```
Step 6: To run mediamtx-authenticator-basic, run:
```
java -jar ./target/mediamtx-authenticator-basic/mediamtx-authenticator-basic.jar
```
### Install as Linux service (systemd)
Step 1: To copy the application root directory to the /opt directory, run:
```
sudo cp /path/to/mediamtx-authenticator-basic /opt
```
Step 2: For enhanced security we first create a specific user to run the service with and change the executable
JAR file permissions accordingly:
```
sudo useradd authenticator
sudo passwd authenticator
sudo chown -R authenticator:authenticator /opt/mediamtx-authenticator-basic
sudo chmod 500 /opt/mediamtx-authenticator-basic/mediamtx-authenticator-basic.jar
```
Step 3: We create configuration file named mediamtx-authenticator-basic.service using the following example and put it in
/etc/systemd/system directory:
```
[Unit]
Description=mediamtx-authenticator-basic
After=syslog.target
After=network.target
After=redis.service
Requires=redis.service

[Service]
StandardOutput=null
StandardError=null
User=authenticator
Group=authenticator
WorkingDirectory=/opt/mediamtx-authenticator-basic
ExecStart=java -jar /opt/mediamtx-authenticator-basic/mediamtx-authenticator-basic.jar
SuccessExitStatus=143
TimeoutStopSec=10
Restart=on-failure
RestartSec=5
OOMScoreAdjust=-100

[Install]
WantedBy=multi-user.target
```
Step 4: To reload systemd unit files, run:
```
sudo systemctl daemon-reload
```
Step 5: To have the service start , run:
```
sudo systemctl start mediamtx-authenticator-basic.service
```
Step 6: To have the service start with the system, run:
```
sudo systemctl enable mediamtx-authenticator-basic.service
```
Step 7: To have the service status, run:
```
sudo systemctl status mediamtx-authenticator-basic.service
```
## Documentation

### API
[POST]
```
http://ip:port/api/auth   POST method with JSON payload. RETURN HTTP STATUS CODE 200,401.
```

Correct JSON payload example:
```json
{
  "ip": "ip",
  "user": "user",
  "password": "password",
  "path": "path",
  "protocol": "rtsp|rtmp|hls|webrtc",
  "id": "id",
  "action": "read|publish",
  "query": "query"
}
```
### Logging
The log files is located in the project root directory in the "logs" directory:
```
./mediamtx-authenticator-basic/logs/
```