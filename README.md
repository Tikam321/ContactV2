-- INSERT INTO contact (usr_id, ctct_usr_id, ctct_nick_nm, fav_yn, del_yn)
-- SELECT 
--     1 AS usr_id,
--     generate_series AS ctct_usr_id,
--     CONCAT('Contact_', generate_series),
--     0,
--     0
-- FROM generate_series(1, 10000);

-- select * from contact;

-- adding groupid to ctct-grp 
-- INSERT INTO ctct_grp (
--     grp_id,
--     grp_nm,
--     grp_disp_ord_no,
--     ownr_usr_id,
--     del_yn,
--     grp_img_url,
--     grp_img_upd_utmx
-- )
-- VALUES (
--     5000,
--     'Performance_Test_Group',
--     1,
--     1,
--     0,
--     'https://dummyimage.com/100x100/000/fff&text=PerfGroup',
--     EXTRACT(EPOCH FROM NOW())
-- );

--  adding 5000 member to groupid 5000
INSERT INTO ctct_grp_memb (
    grp_id,
    usr_id,
    ctct_usr_id,
    grp_nm,
    grp_disp_ord_no,
    ownr_usr_id,
    del_yn
)
SELECT
    5000 AS grp_id,
    1 AS usr_id,
    gs AS ctct_usr_id,
    'TestGroupName' AS grp_nm,
    1 AS grp_disp_ord_no,
    1 AS ownr_usr_id,
    0 AS del_yn
FROM generate_series(1, 10000) AS gs;

-- SELECT count(*) FROM ctct_grp_memb
-- WHERE grp_id = 5000;
-- select count(*) from ctct_grp_memb;

-- select * from ctct_grp;

-- update ctct_grp as grp 
-- grp.grp_nm = "testgroup"
-- where grp.grp_id = 5000


# CI/CD & Deployment Workflow Documentation

**Author:** Tikam Suvasiya
**Tools Used:** GitHub Actions, Docker, Helm, Kubernetes, AWS ECR, kubectl

---

## 1. Overview of Deployment Architecture

Our deployment pipeline consists of:

1. **GitHub Actions** – CI pipeline for building, testing, pushing Docker images.
2. **Docker** – Containerizing the application.
3. **AWS ECR** – Storing Docker images.
4. **Helm Charts** – Deployment configuration templates.
5. **Kubernetes (EKS / local cluster)** – Running workloads.
6. **kubectl** – CLI tool for cluster operations.

---

## 2. GitHub Actions CI/CD Pipeline

### **2.1 Key Steps in Pipeline**

* Checkout repository
* Build JAR / Node project
* Build Docker image
* Login to AWS ECR
* Push Docker image to ECR
* Deploy to Kubernetes using Helm

### **2.2 GitHub Actions YAML (sample)**

```yaml
name: CI-CD Pipeline

on:
  push:
    branches: [ "main" ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout Repo
        uses: actions/checkout@v3

      - name: Set up JDK
        uses: actions/setup-java@v3
        with:
          java-version: '17'

      - name: Build Spring Boot App
        run: mvn clean package -DskipTests

      - name: Build Docker Image
        run: docker build -t $IMAGE_NAME .

      - name: Login to ECR
        run: |
          aws ecr get-login-password \
            | docker login --username AWS --password-stdin $ECR_REPO

      - name: Push Image
        run: docker push $ECR_REPO/$IMAGE_NAME:latest

      - name: Deploy using Helm
        run: |
          helm upgrade --install contact-service ./helm-chart \
          --namespace apps -f values.yaml
```

---

## 3. Docker Usage

### **3.1 Build Docker Image**

```
docker build -t contact-app:latest .
```

### **3.2 Run Locally**

```
docker run -p 8080:8080 contact-app
```

### **3.3 Tag Image for ECR**

```
docker tag contact-app:latest <aws_account>.dkr.ecr.<region>.amazonaws.com/contact-app:latest
```

### **3.4 Push to ECR**

```
docker push <ecr-repo-url>/contact-app:latest
```

---

## 4. Helm Chart Configuration

### **4.1 Deploy Helm Chart**

```
helm install contact-app ./contact-app -n apps
```

### **4.2 Upgrade Deployment**

```
helm upgrade contact contact-app/ -n apps -f contact-app/values.yaml
```

### **4.3 Debug Helm Chart**

```
helm template contact-app ./contact-app
helm lint contact-app
```

### **4.4 Common values.yaml fields modified**

```
image:
  repository: <ECR Repo>
  tag: latest

env:
  - name: POSTGRES_HOST
    value: postgres.apps.svc.cluster.local
  - name: SPRING_PROFILES_ACTIVE
    value: prod
```

---

## 5. Kubernetes Commands Used

### **5.1 Apply YAML File**

```
kubectl apply -f deployment.yaml
```

### **5.2 Check Pods**

```
kubectl get pods -n apps
kubectl describe pod <pod-name> -n apps
```

### **5.3 View Logs**

```
kubectl logs <pod-name> -n apps
```

### **5.4 Restart Deployment**

```
kubectl rollout restart deployment contact-app -n apps
```

### **5.5 Port Forward**

```
kubectl port-forward svc/contact-app 8080:8080 -n apps
```

### **5.6 Debug Deployment Error**

Example error you fixed:

```
Invalid value: "": a lowercase RFC 1123 subdomain must consist of lower case alphanumeric characters
```

This happens when **env.secretKeyRef.name** is empty.

---

## 6. AWS ECR Commands Used

### Login:

```
aws ecr get-login-password \
 | docker login --username AWS --password-stdin <ecr-repo-url>
```

### List Images:

```
aws ecr list-images --repository-name contact-app
```

---

## 7. Common Issues & Fixes

### **Issue 1: Helm upgrade failed due to empty secret reference**

Root cause:

```
spec.template.spec.containers[0].env[6].valueFrom.secretKeyRef.name: Invalid value: ""
```

✔ Fixed by adding correct secret name in `values.yaml`.

---

### **Issue 2: Deployment not updating after image push**

Cause: ImagePullPolicy set incorrectly.
Fix:

```
imagePullPolicy: Always
```

---

### **Issue 3: Pod CrashLoopBackoff**

Fix steps:

* Check logs
* Check configMaps
* Check env variable errors
* Check DB connection

---

## 8. End-to-End Deployment Flow Summary

1. Developer pushes code → GitHub Actions triggers
2. Build JAR and Docker image
3. Login to AWS ECR and push image
4. Helm deploys new version to Kubernetes
5. Kubernetes pulls image → starts pods
6. Service exposed via LoadBalancer or Ingress

---

## 9. Useful kubectl Commands (Daily Use)

```
kubectl get all -n apps
kubectl describe deployment contact-app -n apps
kubectl get svc -n apps
kubectl exec -it <pod> -- /bin/bash
kubectl set image deployment/contact-app contact-app=<image-url> -n apps
```

---

## 10. Useful Helm Commands (Daily Use)

```
helm list -n apps
helm repo update
helm uninstall contact-app -n apps
```

---

# ✔ This Documentation Contains:

* All tools you used
* All configurations you modified
* All commands you executed
* All typical errors & solutions
* End-to-end deployment lifecycle

You can store this as internal knowledgebase or attach it in project README.

