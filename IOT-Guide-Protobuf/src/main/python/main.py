import socket
from time import sleep
import user_pb2
from google.protobuf.internal import encoder

NUM_OF_TESTS = 5
PORT = 8888

def send_over_socket(sock, message):
    delimiter = encoder._VarintBytes(len(message))
    message = delimiter + message
    msg_len = len(message)
    total_sent = 0
    while total_sent < msg_len:
        sent = sock.send(message[total_sent:])
        if sent == 0:
            raise RuntimeError('Socket connection broken')
        total_sent = total_sent + sent


def main():

    # Connect over TCP socket
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect(('localhost', PORT))

    for i in range(NUM_OF_TESTS):
        print('Starting test {}'.format(i))

        command = message_pb2.Command()
        command.type = message_pb2.Command.START_TEST
        command.name = 'Test Case {}'.format(i)
        command.data = 'Test Data'

        send_over_socket(sock, command.SerializeToString())
        sleep(2)

    command = message_pb2.Command()
    command.type = message_pb2.Command.SHUTDOWN
    command.name = 'Shutting down'.format(i)
    send_over_socket(sock, command.SerializeToString())


if __name__ == '__main__':
    main()

