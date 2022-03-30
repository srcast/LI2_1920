#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>
#include <stdbool.h>
#include <time.h>
#include <gmodule.h>

#include "../include/const.h"
#include "../include/interface.h"
#include "../include/navegador.h"
#include "../include/sgv.h"
#include "../include/view.h"

static void swap(int *a, int *b) {
	int tmp = *a;
	*a = *b;
	*b = tmp;
}

static int getOpt() {
	int opcao;
	scanf("%d%*c", &opcao);
	printf("opcao: %d\n", opcao);
	return opcao;
}

static void opt0(SGV sgv) {
    destroySGV(sgv);
    printMensagemSaida();
    exit(EXIT_SUCCESS);
}

static void opt1(SGV sgv) {
	char clientes_filename[] = "input_files/Clientes.txt";
	char produtos_filename[] = "input_files/Produtos.txt";
	char vendas_filename[] = "input_files/Vendas_1M.txt";
	char opcao;
	clock_t start, end; 
	start = clock();
	sgv = loadSGVFromFiles(sgv, clientes_filename, produtos_filename, vendas_filename);
	end = clock();
	if(!getFilesSuccess(sgv)) printFileError();
	double cpu_time_upsed = ((double) end - start) / CLOCKS_PER_SEC;
	printTime(cpu_time_upsed);   
}

static void opt2(SGV sgv) {
    char letra;
    clock_t start, end;
    printf("Introduza uma letra\n");
    scanf("%c%*c", &letra);
    if(islower(letra)) letra = toupper(letra);
    start = clock();
    GPtrArray* arr = getProductsStartedByLetter(sgv, letra);
    end = clock();
    navegarArray(arr);
    g_ptr_array_free(arr, TRUE);
    double cpu_time_upsed = ((double) end - start) / CLOCKS_PER_SEC;
    printTime(cpu_time_upsed);
}

static void opt3(SGV sgv) {
	int mes = 2, filial = 0;
	char codProd[PROD_LEN+1];
	clock_t start, end;
	printf("Introduzir o codigo do produto\n");
	scanf("%s%*c", codProd);
	while(!validaProduto(codProd)) {
		printf("Introduzir um codigo válido\n");
		scanf("%s%*c", codProd);
	}
	printf("Selecionar o mes\n");
	scanf("%d%*c", &mes); 
	while(mes <= 0 || mes > N_MESES) {
    	printf("Introduzir um mes valido:\n");
    	scanf("%d%*c", &mes);
    }
	printf("Selecionar a filial\n");
	scanf("%d%*c", &filial);
	while(filial < 0 || filial > N_FILIAIS) {
    	printf("Introduzir uma filial valida:\n");
    	scanf("%d%*c", &filial);
    }
	start = clock();
	QUERY3 r = getProductSalesAndProfit(sgv, codProd, mes);
	end = clock();
	int* vendas = getNVendasGlobalQuery3(r);
	double* total = getTotalFaturadoGlobalQuery3(r);
	if(filial == TODAS) {
		int* vendas = getNVendasGlobalQuery3(r);
		double* total = getTotalFaturadoGlobalQuery3(r);
		query3outputGlobal(vendas, total);
	}
	else {
		int** vendas = getNVendasQuery3(r);
		double** total = getTotalFaturadoQuery3(r);
		query3outputFilial(vendas, total);
	}
	destroyQuery3(r);
	double cpu_time_upsed = ((double) end - start) / CLOCKS_PER_SEC;
	printTime(cpu_time_upsed);
}

void opt4(SGV sgv) {
	int filial;
	printf("Selecionar uma filial\n");
	scanf("%d%*c", &filial); 
	while(filial < 0 || filial > N_FILIAIS) {
    	printf("Introduzir uma filial valida:\n");
    	scanf("%d%*c", &filial);
    }
    clock_t start, end;
    start = clock();
    GPtrArray* arr = getProductsNeverBought(sgv, filial);
    end = clock();
    navegarArray(arr);
    g_ptr_array_free(arr, TRUE);
	double cpu_time_upsed = ((double) end - start) / CLOCKS_PER_SEC;
	printTime(cpu_time_upsed); 
}

static void opt5(SGV sgv) {
	clock_t start, end;
    start = clock();
    GPtrArray* arr = getClientsofAllBranches(sgv);
    end = clock();
    navegarArray(arr);
    g_ptr_array_free(arr, TRUE);
	double cpu_time_upsed = ((double) end - start) / CLOCKS_PER_SEC;
	printTime(cpu_time_upsed); 
}

static void opt6(SGV sgv) {
	clock_t start, end;
	start = clock();
	int* r = getClientsAndProductsNeverBoughCount(sgv);
	end = clock();
	query6output(r);
	free(r);
	double cpu_time_upsed = ((double) end - start) / CLOCKS_PER_SEC;
	printTime(cpu_time_upsed); 
}

static void opt7(SGV sgv) {
	char codCliente[CLIENTE_LEN +1];
	clock_t start, end;
	printf("Introduzir o código do cliente\n");
	scanf("%s", codCliente);
	while(!validaCliente(codCliente)) {
    	printf("Introduzir um codigo valido:\n");
        scanf("%s", codCliente);
    }
	start = clock();
	int** r = getProductsBoughtByClient(sgv, codCliente);
	end = clock(); 
	query7output(r);
	for(int i=0; i<N_MESES; i++) free(r[i]);
	free(r);
	double cpu_time_upsed = ((double) end - start) / CLOCKS_PER_SEC;
	printTime(cpu_time_upsed); 
}

static void opt8(SGV sgv) {
	int mes1, mes2;
	clock_t start, end;
    printf("Selecionar o primeiro mes\n");
	scanf("%d%*c", &mes1); 
    while(mes1 <= 0 || mes1 > N_MESES) {
    	printf("Introduzir um mes valido:\n");
    	scanf("%d%*c", &mes1);
    }
    printf("Selecionar o segundo mes\n");
	scanf("%d%*c", &mes2);
	while(mes2 <= 0 || mes2 > N_MESES) {
    	printf("Introduzir um mes valido:\n");
    	scanf("%d%*c", &mes2);
    }
	if(mes1 > mes2) swap(&mes1, &mes2);
	start = clock();
	QUERY8 r = getSalesAndProfit(sgv, mes1, mes2);
	end = clock();
	query8output(getNVendasQuery8(r), getTotalFaturadoQuery8(r));
	destroyQuery8(r);
}

static void opt9(SGV sgv) {
	char codProd[PROD_LEN +1];
	int filial;
	printf("Introduzir o codigo do produto\n");
	scanf("%s%*c", codProd);
	while(!validaProduto(codProd)) {
		printf("Introduzir um codigo válido\n");
		scanf("%s%*c", codProd);
	}
	printf("Selecionar a filial\n");
	scanf("%d%*c", &filial);
	while(filial < 0 || filial > N_FILIAIS) {
    	printf("Introduzir uma filial valida:\n");
    	scanf("%d%*c", &filial);
    }
	clock_t start, end;
	start = clock();
	GPtrArray** arr = getProductBuyers(sgv, codProd, filial);
	end = clock();
	query9output(arr);
	for(int i=0; i<TIPOS_VENDA; i++) g_ptr_array_free(arr[i], FALSE);
	free(arr);
	double cpu_time_upsed = ((double) end - start) / CLOCKS_PER_SEC;
	printTime(cpu_time_upsed);   

}

static void opt10(SGV sgv) {
	char codCliente[CLIENTE_LEN+1];
	int mes, n;
	clock_t start, end;
    printf("Inserir o codigo do cliente:\n");
    scanf("%s",codCliente);
    while(!validaCliente(codCliente)) {
    	printf("Introduzir um codigo valido:\n");
        scanf("%s", codCliente);
    }
    printf("Selecionar um mes:\n");
    scanf("%d", &mes);
    while(mes <= 0 || mes > N_MESES) {
    	printf("Introduzir um mes valido:\n");
    	scanf("%d%*c", &mes);
    }
    printf("Introduzir o valor de N\n");
    scanf("%d%*c", &n);
    start = clock();
    GPtrArray* arr = getClientFavoriteProducts(sgv, codCliente, mes);
    end = clock();
    query10output(arr, n);
	g_ptr_array_free(arr, TRUE);
    double cpu_time_upsed = ((double) end - start) / CLOCKS_PER_SEC;
    printTime(cpu_time_upsed);     
}

static void opt11(SGV sgv) {
	int n;
	printf("Introduzir o valor de N\n");
    scanf("%d%*c", &n);
	clock_t start, end;
	start = clock();
	QUERY11 r = getTopSelledProducts(sgv, n);
	end = clock();
	GPtrArray* arr = getProdutosQuery11(r);
	query11output(arr, n);
	double cpu_time_upsed = ((double) end - start) / CLOCKS_PER_SEC;
    printTime(cpu_time_upsed);   
}

static void opt12(SGV sgv) {
	char codCliente[CLIENTE_LEN+1];
	int n;
	clock_t start, end;
	printf("Inserir o codigo do cliente:\n");
    scanf("%s", codCliente);
    while(!validaCliente(codCliente)) {
    	printf("Introduzir um codigo valido:\n");
        scanf("%s", codCliente);
    }
    printf("Introduzir o valor de N\n");
    scanf("%d%*c", &n);
    printf("valor de N %d\n", n);
    start = clock();
    GPtrArray* arr = getClientTopProfitProducts(sgv, codCliente, n);
    end = clock();
    query12output(arr);
    g_ptr_array_free(arr, TRUE);
	double cpu_time_upsed = ((double) end - start) / CLOCKS_PER_SEC;
    printTime(cpu_time_upsed); 

}

static void opt13(SGV sgv) {
	char* clientes_filename = getClientesFileName(sgv);
	int clientes_lidos = getClientesLidos(sgv);
	int clientes_validos = getClientesValidos(sgv);
	char* produtos_filename = getProdutosFileName(sgv);
	int produtos_lidos = getProdutosLidos(sgv);
	int produtos_validos = getProdutosValidos(sgv);
	char* vendas_filename = getVendasFileName(sgv);
	int vendas_lidas = getVendasLidas(sgv);
	int vendas_validas = getVendasValidas(sgv);
	query13output(clientes_filename, clientes_lidos, clientes_validos, produtos_filename, produtos_lidos, produtos_validos, vendas_filename, vendas_lidas, vendas_validas);
	free(clientes_filename);
    free(produtos_filename);
    free(vendas_filename);
}

void initController() {
	SGV sgv = initSGV();
	bool estruturas_inicializadas = false;
	menu();
	int opcao = getOpt();

	while(true) {
		if(opcao == 0) opt0(sgv);
		if(opcao == 1) {
			opt1(sgv);
			if(getFilesSuccess(sgv)) estruturas_inicializadas = true;
			sleep(5);
			system("clear");
			menu();
			opcao = getOpt();
		}
		if(opcao == 2) {
			if(!estruturas_inicializadas) printEstruturasNaoInicializadas();
			else opt2(sgv);
			sleep(5);
			system("clear");
			menu();
			opcao = getOpt();
		}
		if(opcao == 3) {
			if(!estruturas_inicializadas) printEstruturasNaoInicializadas();
			else opt3(sgv);
			sleep(5);
			system("clear");
			menu();
			opcao = getOpt();
		}
		if(opcao == 4) {
			if(!estruturas_inicializadas) printEstruturasNaoInicializadas();
			else opt4(sgv);
			sleep(5);
			system("clear");
			menu();
			opcao = getOpt();	
		}
		if(opcao == 5) {
			if(!estruturas_inicializadas) printEstruturasNaoInicializadas();
			else opt5(sgv);
			sleep(5);
			system("clear");
			menu();
			opcao = getOpt();
		}
		if(opcao == 6) {
			if(!estruturas_inicializadas) printEstruturasNaoInicializadas();
			else opt6(sgv);
			sleep(5);
			system("clear");
			menu();
			opcao = getOpt();
		}
		if(opcao == 7) {
			if(!estruturas_inicializadas) printEstruturasNaoInicializadas();
			else opt7(sgv);
			sleep(5);
			system("clear");
			menu();
			opcao = getOpt();
		}
		if(opcao == 8) {
			if(!estruturas_inicializadas) printEstruturasNaoInicializadas();
			else opt8(sgv);
			sleep(5);
			system("clear");
			menu();
			opcao = getOpt();
		}
		if(opcao == 9) {
			if(!estruturas_inicializadas) printEstruturasNaoInicializadas();
			else opt9(sgv);
			sleep(5);
			system("clear");
			menu();
			opcao = getOpt();
		}
		if(opcao == 10) {
			if(!estruturas_inicializadas) printEstruturasNaoInicializadas();
			else opt10(sgv);
			sleep(5);
			system("clear");
			menu();
			opcao = getOpt();
		}
		if(opcao == 11) {
			if(!estruturas_inicializadas) printEstruturasNaoInicializadas();
			else opt11(sgv);
			sleep(5);
			system("clear");
			menu();
			opcao = getOpt();
		}
		if(opcao == 12) {
			if(!estruturas_inicializadas) printEstruturasNaoInicializadas();
			else opt12(sgv);
			sleep(5);
			system("clear");
			menu();
			opcao = getOpt();
		}
		if(opcao == 13) {
			if(!estruturas_inicializadas) printEstruturasNaoInicializadas();
			else opt13(sgv);
			sleep(5);
			system("clear");
			menu();
			opcao = getOpt();
		}
	}
}