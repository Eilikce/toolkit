package com.eilikce.toolkit.kits.elasticsearch;

import com.eilikce.toolkit.action.BaseAction;
import com.eilikce.toolkit.file.FileUtil;

import java.util.List;

public class FileListAction implements BaseAction<List<String>> {

    @Override
    public List<String> doAction() {
        return FileUtil.fileList(".");
    }

}
