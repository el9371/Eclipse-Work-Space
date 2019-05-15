#include <stdio.h>
#include <stdlib.h>
#include <winsock2.h>
#include <windows.h>
#define PORT 80

void ErrorHandling(int x);

int main()
{
	char message[100];
	WSADATA wsaData;
	SOCKET my_sock, your_sock;
	SOCKADDR_IN my_addr, your_addr;
	int sockaddr_in_size;

	if (WSAStartup(MAKEWORD(2, 2), &wsaData) == -1)
		ErrorHandling(1);
		
	if (my_sock = socket(PF_INET, SOCK_STREAM, 0) == -1)
		ErrorHandling(2);
		
	my_addr.sin_family = AF_INET;
	my_addr.sin_addr.S_un.S_addr = htonl(INADDR_ANY);
	my_addr.sin_port = htons(80);
	
	if(bind(my_sock, (SOCKADDR*)&my_addr, sizeof(my_addr)) == SOCKET_ERROR)
		ErrorHandling(3);	//설정된 주소를 bind함수로 소켓에 할당!



	if(listen(my_sock, 5) == SOCKET_ERROR)	//여기서 클라이언트 접속요청때까지 계속 기다린다!
		ErrorHandling(4);

	while(1) {
		sockaddr_in_size = sizeof(your_addr);
		your_sock=accept(my_sock, (SOCKADDR*)&your_addr, &sockaddr_in_size);
		if(your_sock==INVALID_SOCKET)
			ErrorHandling(5);
		recv(your_sock, message, sizeof(message) - 1, 0);
		printf("%s\n",message);
	}


	closesocket(your_sock);	//소켓들을 정리해주고
	closesocket(my_sock);
	WSACleanup();	//윈속 초기화
	return 0;
}

void ErrorHandling(int x) {
	printf("%d\n",x);
} 
