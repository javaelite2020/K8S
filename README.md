# k8spoc1
1. Create a sample hello world project
2. Deploy on Tomcat 9 
3. Get request in command prompt --> curl --request GET http://localhost:8080/K8s-helloworld/k8s/helloworld
4. Create mvnw and mvnw.cmd using "mvn -N io.takari:maven:wrapper"
5. Commit code in github
6. Login to Google Cloud platform (refer https://codelabs.developers.google.com/codelabs/cloud-deploy-website-on-gke)
7. Create a new project in GCP (ex. k8s-poc-1 and project id is k8s-poc-id-1)
8. Open Cloud shell to run commands
9. Verify the id by running the command --> echo $GOOGLE_CLOUD_PROJECT
10. Set default timezone --> gcloud config set compute/zone us-central1-f
11. Create a GKE cluster
	a. First enable proper API's --> gcloud services enable container.googleapis.com
	b. now create the cluster --> gcloud container clusters create k8s-poc-cluster --num-nodes 3
	c. Verify the cluster instances --> gcloud compute instances list
	d. You can also verify at Menu --> Kubernetes Engine --> Clusters
12. Clone the source repository from GitHub
	a. Clone repo --> "git clone https://github.com/css862/k8spoc1.git"
	b. cd to the repo directory --> k8spoc1
	b. Run "./mvnw clean install"
13. Create a Dockerfile
	a. touch Dockerfile
	b. vi Dockerfile to use tomcat docker and copy war using below in Dockerfile
			FROM tomcat
			COPY /target/K8s-helloworld.war /usr/local/tomcat/webapps/
14. Enable Container Registry to store the container image that you'll create.
	a. $ gcloud services enable containerregistry.googleapis.com
15. Build th docker image using Dockerfile
	a. docker build --no-cache -t <REGISTRY>/<IMAGE>:<TAG> .
		ex: docker build -t docker build -t gcr.io/k8s-poc-id-1/k8spoc1/hello-world:v1 .
		ex2: docker build -t docker build -t us-central1-docker.pkg.dev/k8s-poc-id-1/k8spoc1/hello-world:v1 .
	b. push it to registry
		gcloud docker -- push gcr.io/k8s-poc-id-1/k8spoc1/hello-world:v1
	c. Test 
		docker run -ti --rm -p 8080:8080 gcr.io/k8s-poc-id-1/k8spoc1/hello-world:v1
16. Create your cluster	
	a. gcloud services enable compute.googleapis.com container.googleapis.com
	b. Create cluster with 2 nodes
		gcloud container clusters create k8s-poc-1-cluster \
	  --num-nodes 2 \
	  --machine-type n1-standard-1 \
	  --zone us-central1-c
	 c. After successful completion of cluster creation you can run 'kubectl version' to verify the version.
17. Deploy app to Kubernetes
	a. kubectl create deployment k8s-poc-1-hello-world \
		--image=gcr.io/k8s-poc-id-1/k8spoc1/hello-world:v1
	b. Verfiy deployments
		kubectl get deployments
	c. Vefiy the app instances
		kubectl get pods
18. Create a service to allow external traffic
	a. kubectl create service loadbalancer k8s-poc-1-hello-world --tcp=8080:8080
	b. Verify service
		kubectl get services
	c. Verify external ip
		curl --request GET http://34.132.86.97:8080/K8s-helloworld/k8s/helloworld
