package com.optimal.task.journal;

import com.optimal.task.message.StateMessage;

public interface IJournalService {

    StateMessage add(JournalDTO dto);

    StateMessage edit(JournalDTO dto, Long id);

    StateMessage delete(Long id);
}
