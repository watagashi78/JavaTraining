package interpret;

import java.util.EventListener;

public interface ModelListener extends EventListener {
	void modelChanged(ModelEvent e);
}