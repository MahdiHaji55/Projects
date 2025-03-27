; reset-rightmost.asm
; CSC 230: Fall 2022
;
; Code provided for Assignment #1
;
; Mike Zastre (2022-Sept-22)

; This skeleton of an assembly-language program is provided to help you
; begin with the programming task for A#1, part (b). In this and other
; files provided through the semester, you will see lines of code
; indicating "DO NOT TOUCH" sections. You are *not* to modify the
; lines within these sections. The only exceptions are for specific
; changes announced on conneX or in written permission from the course
; instructor. *** Unapproved changes could result in incorrect code
; execution during assignment evaluation, along with an assignment grade
; of zero. ****
;
; In a more positive vein, you are expected to place your code with the
; area marked "STUDENT CODE" sections.

; ==== BEGINNING OF "DO NOT TOUCH" SECTION ====
; Your task: You are to take the bit sequence stored in R16,
; and to reset the rightmost contiguous sequence of set
; by storing this new value in R25. For example, given
; the bit sequence 0b01011100, resetting the right-most
; contigous sequence of set bits will produce 0b01000000.
; As another example, given the bit sequence 0b10110110,
; the result will be 0b10110000.
;
; Your solution must work, of course, for bit sequences other
; than those provided in the example. (How does your
; algorithm handle a value with no set bits? with all set bits?)

; ANY SIGNIFICANT IDEAS YOU FIND ON THE WEB THAT HAVE HELPED
; YOU DEVELOP YOUR SOLUTION MUST BE CITED AS A COMMENT (THAT
; IS, WHAT THE IDEA IS, PLUS THE URL).

    .cseg
    .org 0

; ==== END OF "DO NOT TOUCH" SECTION ==========

	ldi R16, 0b01011100
	; ldi R16, 0b10110110


	; THE RESULT **MUST** END UP IN R25

; **** BEGINNING OF "STUDENT CODE" SECTION **** 

; Your solution here.

ldi R17, 0b00000001 ; Mask for checking bits, starting from LSB
ldi R18, 0          ; Flag to indicate if we've found a '1' bit
mov R25, R16        ; Copy R16 to R25 to perform operations

loop:
    ; Check if the current bit is '1'
    mov R19, R25      ; Create a working copy of R25
    and R19, R17      ; Bitwise AND to isolate the current bit
    breq check_flag   ; If the result is zero, check the flag
    
    ; If current bit is '1', reset it in R25
    com R17           ; Complement (invert) the mask
    and R25, R17      ; Reset the corresponding bit
    com R17           ; Restore the original mask
    ldi R18, 1        ; Set the flag
    rjmp shift_mask   ; Continue to the next bit
    
check_flag:
    ; If the current bit is '0' and we've found a '1' previously, end
    tst R18           ; Test if the flag is set
    brne end_loop     ; If it is, end the loop
    
shift_mask:
    ; Shift the mask and continue the loop
    lsl R17           ; Logical shift left to move to the next bit
    brcc loop         ; If the carry bit is clear, we've not checked all 8 bits

end_loop:



    









; **** END OF "STUDENT CODE" SECTION ********** 



; ==== BEGINNING OF "DO NOT TOUCH" SECTION ====
reset_rightmost_stop:
    rjmp reset_rightmost_stop


; ==== END OF "DO NOT TOUCH" SECTION ==========
