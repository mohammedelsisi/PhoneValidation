docker build -t phone-validation-backend:v1.0 PhoneVerificationBackEnd 
docker pull mohamedelsisi/phone-validation-frontend
start cmd /k docker run -p 9090:9090 phone-validation-backend:v1.0
start cmd /k docker run -p 8085:80 mohamedelsisi/phone-validation-frontend:latest
