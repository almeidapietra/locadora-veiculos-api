package edu.adatech.locadoradeveiculos.service;
import edu.adatech.locadoradeveiculos.enums.TipoVeiculo;
import edu.adatech.locadoradeveiculos.model.VeiculoModel;
import edu.adatech.locadoradeveiculos.repository.VeiculoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VeiculoServiceTest {

    @InjectMocks
    private VeiculoService veiculoService;

    @Mock
    private VeiculoRepository veiculoRepository;

    @Test
    @DisplayName("Deve cadastrar um veículo com sucesso")
    void testCadastrarVeiculo() {
        VeiculoModel veiculo = new VeiculoModel();
        veiculo.setModelo("Kicks");
        veiculo.setTipo(TipoVeiculo.CARRO);

        when(veiculoRepository.findByModelo("Kicks")).thenReturn(new ArrayList<>());
        when(veiculoRepository.save(veiculo)).thenReturn(veiculo);

        VeiculoModel resultado = veiculoService.cadastrarVeiculo(veiculo);

        assertNotNull(resultado);
        assertEquals("Kicks", resultado.getModelo());
        verify(veiculoRepository).save(veiculo);
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar veículo sem tipo")
    void testCadastrarVeiculoSemTipo() {
        VeiculoModel veiculo = new VeiculoModel();
        veiculo.setModelo("Kicks");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> veiculoService.cadastrarVeiculo(veiculo));

        assertEquals("Erro: Tipo de veículo é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar veículo já existente")
    void testCadastrarVeiculoJaExistente() {
        VeiculoModel veiculo = new VeiculoModel();
        veiculo.setModelo("Kicks");
        veiculo.setTipo(TipoVeiculo.CARRO);

        when(veiculoRepository.findByModelo("Kicks")).thenReturn(List.of(veiculo));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> veiculoService.cadastrarVeiculo(veiculo));

        assertEquals("Erro: Veículo já existe.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve alterar um veículo existente")
    void testAlterarVeiculo() {
        VeiculoModel veiculoExistente = new VeiculoModel();
        veiculoExistente.setId(1L);
        veiculoExistente.setModelo("Kicks");

        VeiculoModel veiculoAtualizado = new VeiculoModel();
        veiculoAtualizado.setModelo("Corolla");
        veiculoAtualizado.setDisponivel(true);

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculoExistente));
        when(veiculoRepository.save(any(VeiculoModel.class))).thenReturn(veiculoAtualizado);

        VeiculoModel resultado = veiculoService.alterarVeiculo(1L, veiculoAtualizado);

        assertNotNull(resultado);
        assertEquals("Corolla", resultado.getModelo());
        verify(veiculoRepository).save(any(VeiculoModel.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar alterar um veículo inexistente")
    void testAlterarVeiculoInexistente() {
        VeiculoModel veiculoAtualizado = new VeiculoModel();
        veiculoAtualizado.setModelo("Corolla");

        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> veiculoService.alterarVeiculo(1L, veiculoAtualizado));

        assertEquals("Veículo não encontrado.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve listar todos os veículos")
    void testListarVeiculos() {
        List<VeiculoModel> veiculos = List.of(new VeiculoModel(), new VeiculoModel());

        when(veiculoRepository.findAll()).thenReturn(veiculos);

        List<VeiculoModel> resultado = veiculoService.listarVeiculos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(veiculoRepository).findAll();
    }

    @Test
    @DisplayName("Deve buscar veículo por modelo")
    void testBuscarVeiculoPorModelo() {
        VeiculoModel veiculo = new VeiculoModel();
        veiculo.setModelo("Kicks");

        when(veiculoRepository.findByModelo("Kicks")).thenReturn(List.of(veiculo));

        VeiculoModel resultado = veiculoService.buscarVeiculoPorModelo("Kicks");

        assertNotNull(resultado);
        assertEquals("Kicks", resultado.getModelo());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar veículo por modelo inexistente")
    void testBuscarVeiculoPorModeloInexistente() {
        when(veiculoRepository.findByModelo("Kicks")).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> veiculoService.buscarVeiculoPorModelo("Kicks"));

        assertEquals("Veículo não encontrado.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve buscar veículo por ID")
    void testBuscarVeiculoPorId() {
        VeiculoModel veiculo = new VeiculoModel();
        veiculo.setId(1L);

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        Optional<VeiculoModel> resultado = veiculoService.buscarVeiculoPorId(1L);

        assertNotNull(resultado, "O resultado não deve ser nulo");
        assertTrue(resultado.isPresent(), "O Optional deve conter um valor");
        //assertEquals(1L, resultado.get().getId(), "O ID do veículo deve ser igual ao esperado");
    }

    @Test
    @DisplayName("Deve deletar veículo por ID")
    void testDeletarVeiculo() {
        when(veiculoRepository.existsById(1L)).thenReturn(true);

        veiculoService.deletarVeiculo(1L);

        verify(veiculoRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar veículo inexistente")
    void testDeletarVeiculoInexistente() {
        when(veiculoRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> veiculoService.deletarVeiculo(1L));

        assertEquals("Erro: Veículo não encontrado.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve listar veículos por tipo")
    void testListarVeiculosPorTipo() {
        List<VeiculoModel> veiculos = List.of(new VeiculoModel(), new VeiculoModel());

        when(veiculoRepository.findByTipo(TipoVeiculo.CARRO)).thenReturn(veiculos);

        List<VeiculoModel> resultado = veiculoService.listarVeiculosPorTipo(TipoVeiculo.CARRO);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }
}
