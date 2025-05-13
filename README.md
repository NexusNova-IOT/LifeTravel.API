# LifeTravel.API

```bash
docker run -d \
  --name sqlserver-lifetravel \
  -e "ACCEPT_EULA=Y" \
  -e "SA_PASSWORD=NexusNova2023" \
  -e "MSSQL_PID=Express" \
  -p 1433:1433 \
  -v $(pwd)/init.sql:/init.sql \
  mcr.microsoft.com/mssql/server:2019-latest
```