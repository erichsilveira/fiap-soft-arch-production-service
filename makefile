k apply -f postgres-service.yaml,postgres-deployment.yaml,postgres-data-persistentvolumeclaim.yaml
k apply -f production-service-backend-service.yaml,production-service-backend-deployment.yaml,production-service-backend-autosacling.yaml

k get hpa
k get pods

k delete pod,svc --all
k delete --all deployments --namespace=default

aws eks --region us-east-1 update-kubeconfig --name apps_cluster
k port-forward production-service-backend-svc 30003:30003
