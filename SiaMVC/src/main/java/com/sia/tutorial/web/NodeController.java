package com.sia.tutorial.web;

import com.sia.tutorial.Node;
import com.sia.tutorial.data.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/nodes")
public class NodeController {

	private static final String MAX_LONG_AS_STRING = "9223372036854775807";

	private NodeRepository nodeRepository;

	@Autowired
	public NodeController(NodeRepository nodeRepository) {
		this.nodeRepository = nodeRepository;
	}

	@RequestMapping(value = "/{nodeId}", method = RequestMethod.GET)
	public String node(@PathVariable("nodeId") long nodeId, Model model) {
		model.addAttribute(nodeRepository.findOne(nodeId));
		return "node";
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Node> nodes(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "count", defaultValue = "20") int count) {
		return nodeRepository.findNodes(max, count);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	  public String saveNode(NodeForm form, Model model) throws Exception {
	    nodeRepository.save(new Node(null, form.getMessage(), new Date(), 
	        form.getLongitude(), form.getLatitude()));
	    return "redirect:/nodes";
	  }

}
