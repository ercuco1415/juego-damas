package core.domain.state.machine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;

public class State implements Serializable{
	
	private static final long serialVersionUID = 5785111086525328932L;
	private String stateMachineName;
	
	public String getStateMachineName() {
		return stateMachineName;
	}

	public void setStateMachineName(String stateMachineName) {
		this.stateMachineName = stateMachineName;
	}

	public String doTransition(String stateName,
			String transitionName) {

		ProcessDefinition definition = getProcessDefinition();
		if (definition == null) {
			return null;
		}
		String returnString = _doTrasition(definition,
				stateName, transitionName);
		return returnString;
	}

	public List<String> getTransitions( String stateName) {
		ProcessDefinition definition = getProcessDefinition();
		if (definition == null) {
			return null;
		}
		List<String> returnList = _getTransitions(definition, stateMachineName,
				stateName);
		return returnList;
	}

	public boolean hasNode(String stateName) {
		ProcessDefinition definition = getProcessDefinition();
		if (definition == null) {
			return false;
		}
		boolean returnboolean = _hasNode(definition, stateMachineName,
				stateName);
		return returnboolean;
	}

	public boolean hasTransition(String stateName,
			String transitionName) {
		List<String> transitions = getTransitions(stateName);
		boolean returnboolean = transitions.contains(transitionName);
		return returnboolean;
	}

	private String _doTrasition(ProcessDefinition definition, String stateName, String transitionName) {
		if (_hasNode(definition, stateMachineName, stateName)) {
			ProcessInstance instance = new ProcessInstance(definition);
			try {
				Token token = instance.getRootToken();
				Node nodo = definition.getNode(stateName);
				if (hasTransition(stateName, transitionName)) {
					token.setNode(nodo);
					token.signal(transitionName);
					String returnString = token.getNode().getName();
					return returnString;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private List<String> _getTransitions(ProcessDefinition definition,
			String stateMachineName, String stateName) {
		List<String> transitions = new ArrayList<String>();
		if (_hasNode(definition, stateMachineName, stateName)) {
			Node node = definition.getNode(stateName);
			if (!node.hasNoLeavingTransitions()) {
				Iterator<Transition> it = node.getLeavingTransitions()
						.iterator();
				while (it.hasNext()) {
					Transition transition = it.next();
					transitions.add(transition.getName());
				}
			}
		}

		return transitions;
	}

	private boolean _hasNode(ProcessDefinition definition,
			String stateMachineName, String stateName) {
		boolean returnboolean = definition.hasNode(stateName);
		return returnboolean;
	}

	private ProcessDefinition getProcessDefinition() {
		ProcessDefinition definition = null;
		try {
			definition = ProcessDefinition.parseXmlResource(this.stateMachineName+ "/processdefinition.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return definition;
	}
}
