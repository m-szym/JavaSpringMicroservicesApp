FROM nginx:1.23.3
LABEL author="msz"
LABEL title="aui-front"

ENV API_URL=http://localhost:8080/api

ADD dist/ng-front/browser /usr/share/nginx/html/
ADD docker/etc/nginx/templates/ /etc/nginx/templates/
