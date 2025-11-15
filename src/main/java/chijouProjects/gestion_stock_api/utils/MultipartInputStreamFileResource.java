package chijouProjects.gestion_stock_api.utils;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class MultipartInputStreamFileResource extends InputStreamResource {
    private final String filename;
    private final long contentLength; // Add this field

    public MultipartInputStreamFileResource(MultipartFile file) throws IOException {
        super(file.getInputStream());
        this.filename = file.getOriginalFilename();
        this.contentLength = file.getSize(); // Capture the content length
    }

    @Override
    public String getFilename() {
        return this.filename;
    }

    @Override
    public long contentLength() {
        // Return the actual size. Returning -1 (as in your original snippet) is generally
        // discouraged for multipart requests as it can break some REST implementations.
        return this.contentLength;
    }
}
