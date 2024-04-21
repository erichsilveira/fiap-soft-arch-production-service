package com.fiap.production.domain.service;

import com.fiap.production.domain.repository.ProductRepository;
import com.fiap.production.interfaces.dto.DeleteProductCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class DeleteProductUseCaseTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private DeleteProductUseCaseImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteProduct() {
        // Dados de entrada
        DeleteProductCommand command = new DeleteProductCommand("123");

        // Chamada ao método a ser testado
        service.deleteProduct(command);

        // Verificação
        verify(repository).deleteProduct("123"); // Verifica se o método do repositório foi chamado corretamente com o ID
    }
}
