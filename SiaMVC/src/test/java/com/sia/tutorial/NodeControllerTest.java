package com.sia.tutorial;

import com.sia.tutorial.data.NodeRepository;
import com.sia.tutorial.web.NodeController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class NodeControllerTest {

	@Test
	public void shouldShowRecentNodes() throws Exception {
		
		List<Node> expectedNodes = createNodeList(20);
		NodeRepository mockRepository = mock(NodeRepository.class);
		when(mockRepository.findNodes(Long.MAX_VALUE, 20)).thenReturn(expectedNodes);

		NodeController controller = new NodeController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/nodes.jsp")).build();

		mockMvc.perform(get("/nodes")).andExpect(view().name("nodes")).andExpect(model().attributeExists("nodeList"))
				.andExpect(model().attribute("nodeList", hasItems(expectedNodes.toArray())));
	}

	@Test
	public void shouldShowPagedNodes() throws Exception {
		
		List<Node> expectedNodes = createNodeList(50);
		NodeRepository mockRepository = mock(NodeRepository.class);
		when(mockRepository.findNodes(238900, 50)).thenReturn(expectedNodes);

		NodeController controller = new NodeController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/nodes.jsp")).build();

		mockMvc.perform(get("/nodes?max=238900&count=50")).andExpect(view().name("nodes"))
				.andExpect(model().attributeExists("nodeList"))
				.andExpect(model().attribute("nodeList", hasItems(expectedNodes.toArray())));
	}

	@Test
	public void testNode() throws Exception {
		
		Node expectedNode = new Node("Hello", new Date());
		NodeRepository mockRepository = mock(NodeRepository.class);
		when(mockRepository.findOne(12345)).thenReturn(expectedNode);
		
		NodeController controller = new NodeController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		mockMvc.perform(get("/nodes/12345")).andExpect(view().name("node")).andExpect(model().attributeExists("node"))
				.andExpect(model().attribute("node", expectedNode));
	}

	@Test
	public void saveNode() throws Exception {
		NodeRepository mockRepository = mock(NodeRepository.class);
		NodeController controller = new NodeController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();

		mockMvc.perform(post("/nodes")
						.param("message", "Hello World") // this works, but isn't really testing what really happens
						.param("longitude", "-81.5811668")
						.param("latitude", "28.4159649"))
				.andExpect(redirectedUrl("/nodes"));

		verify(mockRepository, atLeastOnce()).save(new Node(null, "Hello World", new Date(), -81.5811668, 28.4159649));
	}

	private List<Node> createNodeList(int count) {
		List<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < count; i++) {
			nodes.add(new Node("Node " + i, new Date()));
		}
		return nodes;
	}

}
