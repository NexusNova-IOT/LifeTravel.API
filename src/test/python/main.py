import paho.mqtt.client as mqtt
import time
import random

# MQTT broker configuration
MQTT_HOST = 'localhost'
MQTT_PORT = 1883
MQTT_TOPIC = 'temperatures/department{}'  # {} will be replaced by the department ID

def on_connect(client, userdata, flags, rc):
    """ Handles the event of connecting to the MQTT broker. """
    print("Connected with result code " + str(rc))

def on_publish(client, userdata, mid):
    """ Handles the event of publishing a message. """
    print("Message Published.")

def publish_temperature(client, department_id):
    """ Publishes a temperature reading to a specific department. """
    temperature = random.uniform(20.0, 30.0)  # Generates a random temperature between 20 and 30
    topic = MQTT_TOPIC.format(department_id)
    result = client.publish(topic, '{:.2f}'.format(temperature))
    status = result[0]
    if status == 0:
        print(f"Send `{temperature:.2f}` to topic `{topic}`")
    else:
        print(f"Failed to send message to topic {topic}")

def main():
    # Initialize MQTT client
    client = mqtt.Client()
    client.on_connect = on_connect
    client.on_publish = on_publish

    # Connect to the MQTT broker
    client.connect(MQTT_HOST, MQTT_PORT, 60)
    client.loop_start()

    # Configuration of message interval and department selection
    interval_input = input("Enter the interval (in seconds) between messages or press Enter for default (5 seconds): ")
    interval = float(interval_input) if interval_input.strip() != "" else 5.0

    department_input = input("Enter department ID (1-24), 'random' for random department, or press Enter for random: ")
    if department_input.strip() == "":
        department_choice = 'random'
    else:
        department_choice = department_input.strip().lower()

    try:
        while True:
            if department_choice == 'exit':
                break
            if department_choice == 'random':
                department_id = random.randint(1, 24)  # Randomly choose from department 1 to 24
            else:
                department_id = int(department_choice)  # Use the specific department ID entered by the user

            publish_temperature(client, department_id)
            time.sleep(interval)  # Wait for the specified interval before sending another message
    finally:
        client.loop_stop()
        client.disconnect()

if __name__ == '__main__':
    main()
