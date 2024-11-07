FROM rabbitmq:3.8-management

# Delayed Message Exchange 플러그인 파일 복사
COPY ./rabbitmq_delayed_message_exchange-3.8.0.ez /opt/rabbitmq/plugins/

# management와 delayed_message_exchange 플러그인 활성화
RUN rabbitmq-plugins enable rabbitmq_management rabbitmq_delayed_message_exchange
